package com.gsu21se45.entity_tmp;

import javax.persistence.*;

@Entity
public class Schedule {
    private int id;
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
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
