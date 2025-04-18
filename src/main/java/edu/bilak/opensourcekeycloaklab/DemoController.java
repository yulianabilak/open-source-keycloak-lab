package edu.bilak.opensourcekeycloaklab;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project open-source-keycloak-lab
 * @class DemoController
 * @since 02/04/2025 — 18.52
 **/
@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping("/user")
    @PreAuthorize("hasRole('app_user')")
    public String userAccess() {
        return "Hello USER!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('app_admin')")
    public String adminAccess() {
        return "Hello ADMIN!";
    }

    @GetMapping("/public")
    public String anonymousAccess() {
        return "Hello from an anonymous endpoint!";
    }
}
