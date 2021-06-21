package com.gsu21se45.entity_tmp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Street {
    private int id;
    private String name;
    private Collection<StreetWard> streetWardsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return id == street.id && Objects.equals(name, street.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "streetByStreetId")
    public Collection<StreetWard> getStreetWardsById() {
        return streetWardsById;
    }

    public void setStreetWardsById(Collection<StreetWard> streetWardsById) {
        this.streetWardsById = streetWardsById;
    }
}
