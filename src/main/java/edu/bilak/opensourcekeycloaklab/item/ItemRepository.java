package edu.bilak.opensourcekeycloaklab.item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project open-source-keycloak-lab
 * @class ItemRepository
 * @since 30/04/2025 — 18.27
 **/
@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
}
