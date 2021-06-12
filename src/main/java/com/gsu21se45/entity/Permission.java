package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Permission {
    private int id;
    private Integer userId;
    private Integer featureId;
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

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "feature_id", nullable = true)
    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return id == that.id && Objects.equals(userId, that.userId) && Objects.equals(featureId, that.featureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, featureId);
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
