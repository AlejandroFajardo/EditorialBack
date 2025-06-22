package com.uptc.edu.backendTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    private static final String GROUPS = "groups";
    private static final String RESOURCE_ACCESS_CLAIM= "resource_access"; //For use if you need resource fine-grained access (needs to re-adapt GrantedAuthoritiesMapper bean)
    private static final String REALM_ACCESS_CLAIM = "realm_access";
    private static final String ROLES_CLAIM = "roles";
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final KeycloakLogoutHandler keycloakLogoutHandler;

    SecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
        this.keycloakLogoutHandler = keycloakLogoutHandler;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        // First we allow preflight options requests with all permission
                        .requestMatchers(new AntPathRequestMatcher("/prestamos*", "OPTIONS")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/libros*", "OPTIONS")).permitAll()
                        // After we retrieve information, only users with the "backendAdmin" role for backend-starter can access to "prestamos" endpoint
                        .requestMatchers(new AntPathRequestMatcher("/prestamos/**")).hasRole("backendAdminClient")
                        // Only users with the "backendUser or backendAdmin" role for backend-starter can access to "libros" endpoint
                        .requestMatchers(new AntPathRequestMatcher("/libros/**")).hasAnyRole("backendUser", "backendAdmin")
                        // All others URLs can be accessed without restriction
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/"));

        // For more security, we can HIDE endpoints with this custom http exception handler, FORBIDDEN (403) resources will appear as NOT FOUND (404)
        http.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedHandler(accessDeniedHandler()));

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Set<GrantedAuthority> authorities = extractRealmRoles(jwt);
            authorities.addAll(extractClientRoles(jwt));
            return authorities;
        });
        return converter;
    }
    //Here we extract Realm roles
    private Set<GrantedAuthority> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get(REALM_ACCESS_CLAIM);
        Collection<String> roles = (Collection<String>) realmAccess.get(ROLES_CLAIM);
        return generateAuthoritiesFromClaim(roles);
    }
    //Here we extract Client roles
    private Set<GrantedAuthority> extractClientRoles(Jwt jwt) {
        Map<String, Map<String, Collection<String>>> resourceAccess = (Map<String, Map<String, Collection<String>>>) jwt.getClaims().get(RESOURCE_ACCESS_CLAIM);
        return resourceAccess.values().stream()
                .flatMap(client -> client.get(ROLES_CLAIM).stream()) //Map for roles
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))//Map to add ROLE_ prefix
                .collect(Collectors.toSet());
    }

    private Set<GrantedAuthority> generateAuthoritiesFromClaim(Collection<String> roles) {
        // We need to add "ROLE_" prefix to meet spring security requirements
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
        };
    }
}