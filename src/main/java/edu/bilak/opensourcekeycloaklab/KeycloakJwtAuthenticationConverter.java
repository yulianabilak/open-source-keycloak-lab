package edu.bilak.opensourcekeycloaklab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Map;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project open-source-keycloak-lab
 * @class KeycloakJwtAuthenticationConverter
 * @since 18/04/2025 — 21.23
 **/
@Component
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final String clientId;

    public KeycloakJwtAuthenticationConverter(@Value("${keycloak.client-id}") String clientId) {
        this.clientId = clientId;
    }

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, authorities);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        @SuppressWarnings("unchecked")
        Collection<String> roles =
                Optional.ofNullable(jwt.<Map<String, Object>>getClaim("resource_access"))
                        .map(resource -> (Map<String, Object>) resource.get(clientId))
                        .map(client   -> (Collection<String>) client.get("roles"))
                        .orElse(Collections.emptyList());

        return roles.stream()
                .filter(Objects::nonNull)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace('-', '_')))
                .collect(Collectors.toUnmodifiableSet());
    }
}
