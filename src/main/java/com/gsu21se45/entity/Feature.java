package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Feature {
    private int id;
    private String name;
    private String description;
    private Collection<Permission> permissionsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return id == feature.id && Objects.equals(name, feature.name) && Objects.equals(description, feature.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @OneToMany(mappedBy = "featureByFeatureId")
    public Collection<Permission> getPermissionsById() {
        return permissionsById;
    }

    public void setPermissionsById(Collection<Permission> permissionsById) {
        this.permissionsById = permissionsById;
    }
}
