package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Permission {
    private int id;
    private User userByUserId;
    private Feature featureByFeatureId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    public Feature getFeatureByFeatureId() {
        return featureByFeatureId;
    }

    public void setFeatureByFeatureId(Feature featureByFeatureId) {
        this.featureByFeatureId = featureByFeatureId;
    }
}
