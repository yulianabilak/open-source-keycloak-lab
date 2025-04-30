package edu.bilak.opensourcekeycloaklab.item;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project open-source-keycloak-lab
 * @class Item
 * @since 30/04/2025 — 18.14
 **/
@Document("item")
public class Item extends BaseDocument {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + getCreatedAt() + '\'' +
                ", createdBy='" + getCreatedBy() + '\'' +
                ", updatedAt=" + getUpdatedAt() + '\'' +
                ", updatedBy='" + getUpdatedBy() + '\'' +
                '}';
    }
}
