package edu.bilak.opensourcekeycloaklab.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project open-source-keycloak-lab
 * @class ItemController
 * @since 30/04/2025 — 18.32
 **/
@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Item>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> get(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('app_admin')")
    public ResponseEntity<Item> create(@RequestBody Item item) {
        item.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(item));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('app_admin')")
    public ResponseEntity<Item> update(@PathVariable String id, @RequestBody Item item) {
        item.setId(id);
        return service.update(item)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('app_admin')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
