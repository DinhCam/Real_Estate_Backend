package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Transaction {
    private int id;
    private Double downPrice;
    private User userByBuyerId;
    private User userBySellerId;
    private User userByStaffId;
    private RealEstate realEstateByRealEstateId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "down_price", nullable = true, precision = 0)
    public Double getDownPrice() {
        return downPrice;
    }

    public void setDownPrice(Double downPrice) {
        this.downPrice = downPrice;
    }

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getUserByBuyerId() {
        return userByBuyerId;
    }

    public void setUserByBuyerId(User userByBuyerId) {
        this.userByBuyerId = userByBuyerId;
    }

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public User getUserBySellerId() {
        return userBySellerId;
    }

    public void setUserBySellerId(User userBySellerId) {
        this.userBySellerId = userBySellerId;
    }

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    public User getUserByStaffId() {
        return userByStaffId;
    }

    public void setUserByStaffId(User userByStaffId) {
        this.userByStaffId = userByStaffId;
    }

    @ManyToOne
    @JoinColumn(name = "real_estate_id", referencedColumnName = "id")
    public RealEstate getRealEstateByRealEstateId() {
        return realEstateByRealEstateId;
    }

    public void setRealEstateByRealEstateId(RealEstate realEstateByRealEstateId) {
        this.realEstateByRealEstateId = realEstateByRealEstateId;
    }
}
