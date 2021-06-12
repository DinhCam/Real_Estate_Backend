package com.gsu21se45.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Appointment {
    private int id;
    private Integer buyerId;
    private Integer sellerId;
    private Integer staffId;
    private Integer realEstateId;
    private Date scheduleDate;
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
    @Column(name = "schedule_date", nullable = true)
    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
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
        Appointment that = (Appointment) o;
        return id == that.id && Objects.equals(buyerId, that.buyerId) && Objects.equals(sellerId, that.sellerId) && Objects.equals(staffId, that.staffId) && Objects.equals(realEstateId, that.realEstateId) && Objects.equals(scheduleDate, that.scheduleDate) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerId, sellerId, staffId, realEstateId, scheduleDate, status);
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
