package com.gsu21se45.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Deal {
    private int id;
    private Integer buyerId;
    private Integer sellerId;
    private Integer realEstateId;
    private Timestamp createAt;
    private Double offeredPrice;
    private Byte status;
    private User userByBuyerId;
    private User userBySellerId;
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
    @Column(name = "real_estate_id", nullable = true)
    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }

    @Basic
    @Column(name = "create_at", nullable = true)
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "offered_price", nullable = true, precision = 0)
    public Double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Double offeredPrice) {
        this.offeredPrice = offeredPrice;
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
        Deal deal = (Deal) o;
        return id == deal.id && Objects.equals(buyerId, deal.buyerId) && Objects.equals(sellerId, deal.sellerId) && Objects.equals(realEstateId, deal.realEstateId) && Objects.equals(createAt, deal.createAt) && Objects.equals(offeredPrice, deal.offeredPrice) && Objects.equals(status, deal.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerId, sellerId, realEstateId, createAt, offeredPrice, status);
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
    @JoinColumn(name = "real_estate_id", referencedColumnName = "id")
    public RealEstate getRealEstateByRealEstateId() {
        return realEstateByRealEstateId;
    }

    public void setRealEstateByRealEstateId(RealEstate realEstateByRealEstateId) {
        this.realEstateByRealEstateId = realEstateByRealEstateId;
    }
}
