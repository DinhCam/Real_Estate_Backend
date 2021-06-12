package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Transaction {
    private int id;
    private Integer buyerId;
    private Integer sellerId;
    private Integer staffId;
    private Integer realEstateId;
    private Double price;
    private Double downPrice;
    private Byte status;
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
    @Column(name = "buyer_id", nullable = true)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    @Basic
    @Column(name = "seller_id", nullable = true)
    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "staff_id", nullable = true)
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "real_estate_id", nullable = true)
    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "down_price", nullable = true, precision = 0)
    public Double getDownPrice() {
        return downPrice;
    }

    public void setDownPrice(Double downPrice) {
        this.downPrice = downPrice;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Objects.equals(buyerId, that.buyerId) && Objects.equals(sellerId, that.sellerId) && Objects.equals(staffId, that.staffId) && Objects.equals(realEstateId, that.realEstateId) && Objects.equals(price, that.price) && Objects.equals(downPrice, that.downPrice) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerId, sellerId, staffId, realEstateId, price, downPrice, status);
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
