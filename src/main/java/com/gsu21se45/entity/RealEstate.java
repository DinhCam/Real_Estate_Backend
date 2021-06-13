package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "real_estate", schema = "real_estate")
public class RealEstate {
    private int id;
    private String title;
    private String view;
    private Byte isActive;
    private Collection<Appointment> appointmentsById;
    private Collection<Conversation> conversationsById;
    private Collection<Deal> dealsById;
    private User userBySellerId;
    private User userByStaffId;
    private RealEstateDetail realEstateDetailsById;
    private Collection<Transaction> transactionsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "view", nullable = true, length = 255)
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Basic
    @Column(name = "is_active", nullable = true)
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @OneToMany(mappedBy = "realEstateByRealEstateId")
    public Collection<Appointment> getAppointmentsById() {
        return appointmentsById;
    }

    public void setAppointmentsById(Collection<Appointment> appointmentsById) {
        this.appointmentsById = appointmentsById;
    }

    @OneToMany(mappedBy = "realEstateByRealEstateId")
    public Collection<Conversation> getConversationsById() {
        return conversationsById;
    }

    public void setConversationsById(Collection<Conversation> conversationsById) {
        this.conversationsById = conversationsById;
    }

    @OneToMany(mappedBy = "realEstateByRealEstateId")
    public Collection<Deal> getDealsById() {
        return dealsById;
    }

    public void setDealsById(Collection<Deal> dealsById) {
        this.dealsById = dealsById;
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

    @OneToOne(mappedBy = "realEstateByRealEstateId")
    public RealEstateDetail getRealEstateDetailsById() {
        return realEstateDetailsById;
    }

    public void setRealEstateDetailsById(RealEstateDetail realEstateDetailsById) {
        this.realEstateDetailsById = realEstateDetailsById;
    }

    @OneToMany(mappedBy = "realEstateByRealEstateId")
    public Collection<Transaction> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<Transaction> transactionsById) {
        this.transactionsById = transactionsById;
    }
}
