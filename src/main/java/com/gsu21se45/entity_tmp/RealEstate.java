package com.gsu21se45.entity_tmp;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "real_estate", schema = "real_estate")
public class RealEstate {
    private int id;
    private String title;
    private String view;
    private String status;
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
    @Column(name = "status", nullable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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