package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Schedule {
    private int id;
    private Integer sellerId;
    private Integer weekDayId;
    private Integer timeFrameId;
    private Byte status;
    private User userBySellerId;
    private WeekDay weekDayByWeekDayId;
    private TimeFrame timeFrameByTimeFrameId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "week_day_id", nullable = true)
    public Integer getWeekDayId() {
        return weekDayId;
    }

    public void setWeekDayId(Integer weekDayId) {
        this.weekDayId = weekDayId;
    }

    @Basic
    @Column(name = "time_frame_id", nullable = true)
    public Integer getTimeFrameId() {
        return timeFrameId;
    }

    public void setTimeFrameId(Integer timeFrameId) {
        this.timeFrameId = timeFrameId;
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
        Schedule schedule = (Schedule) o;
        return id == schedule.id && Objects.equals(sellerId, schedule.sellerId) && Objects.equals(weekDayId, schedule.weekDayId) && Objects.equals(timeFrameId, schedule.timeFrameId) && Objects.equals(status, schedule.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sellerId, weekDayId, timeFrameId, status);
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
    @JoinColumn(name = "week_day_id", referencedColumnName = "id")
    public WeekDay getWeekDayByWeekDayId() {
        return weekDayByWeekDayId;
    }

    public void setWeekDayByWeekDayId(WeekDay weekDayByWeekDayId) {
        this.weekDayByWeekDayId = weekDayByWeekDayId;
    }

    @ManyToOne
    @JoinColumn(name = "time_frame_id", referencedColumnName = "id")
    public TimeFrame getTimeFrameByTimeFrameId() {
        return timeFrameByTimeFrameId;
    }

    public void setTimeFrameByTimeFrameId(TimeFrame timeFrameByTimeFrameId) {
        this.timeFrameByTimeFrameId = timeFrameByTimeFrameId;
    }
}
