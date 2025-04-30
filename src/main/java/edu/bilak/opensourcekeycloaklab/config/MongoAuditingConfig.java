package edu.bilak.opensourcekeycloaklab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project open-source-keycloak-lab
 * @class MongoAuditingConfig
 * @since 30/04/2025 — 19.47
 **/
@Configuration
@EnableMongoAuditing
public class MongoAuditingConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
