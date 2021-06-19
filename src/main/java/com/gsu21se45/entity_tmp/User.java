package com.gsu21se45.entity_tmp;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
    private int id;
    private String username;
    private String password;
    private Collection<Appointment> appointmentsById;
    private Collection<Appointment> appointmentsById_0;
    private Collection<Appointment> appointmentsById_1;
    private Collection<Conversation> conversationsById;
    private Collection<Conversation> conversationsById_0;
    private Collection<Deal> dealsById;
    private Collection<Deal> dealsById_0;
    private Collection<Permission> permissionsById;
    private Collection<RealEstate> realEstatesById;
    private Collection<RealEstate> realEstatesById_0;
    private Collection<Schedule> schedulesById;
    private Collection<Transaction> transactionsById;
    private Collection<Transaction> transactionsById_0;
    private Collection<Transaction> transactionsById_1;
    private Role roleByRoleId;
    private UserProfile userProfileByProfileId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "userByBuyerId")
    public Collection<Appointment> getAppointmentsById() {
        return appointmentsById;
    }

    public void setAppointmentsById(Collection<Appointment> appointmentsById) {
        this.appointmentsById = appointmentsById;
    }

    @OneToMany(mappedBy = "userBySellerId")
    public Collection<Appointment> getAppointmentsById_0() {
        return appointmentsById_0;
    }

    public void setAppointmentsById_0(Collection<Appointment> appointmentsById_0) {
        this.appointmentsById_0 = appointmentsById_0;
    }

    @OneToMany(mappedBy = "userByStaffId")
    public Collection<Appointment> getAppointmentsById_1() {
        return appointmentsById_1;
    }

    public void setAppointmentsById_1(Collection<Appointment> appointmentsById_1) {
        this.appointmentsById_1 = appointmentsById_1;
    }

    @OneToMany(mappedBy = "userByBuyerId")
    public Collection<Conversation> getConversationsById() {
        return conversationsById;
    }

    public void setConversationsById(Collection<Conversation> conversationsById) {
        this.conversationsById = conversationsById;
    }

    @OneToMany(mappedBy = "userBySellerId")
    public Collection<Conversation> getConversationsById_0() {
        return conversationsById_0;
    }

    public void setConversationsById_0(Collection<Conversation> conversationsById_0) {
        this.conversationsById_0 = conversationsById_0;
    }

    @OneToMany(mappedBy = "userByBuyerId")
    public Collection<Deal> getDealsById() {
        return dealsById;
    }

    public void setDealsById(Collection<Deal> dealsById) {
        this.dealsById = dealsById;
    }

    @OneToMany(mappedBy = "userBySellerId")
    public Collection<Deal> getDealsById_0() {
        return dealsById_0;
    }

    public void setDealsById_0(Collection<Deal> dealsById_0) {
        this.dealsById_0 = dealsById_0;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Permission> getPermissionsById() {
        return permissionsById;
    }

    public void setPermissionsById(Collection<Permission> permissionsById) {
        this.permissionsById = permissionsById;
    }

    @OneToMany(mappedBy = "userBySellerId")
    public Collection<RealEstate> getRealEstatesById() {
        return realEstatesById;
    }

    public void setRealEstatesById(Collection<RealEstate> realEstatesById) {
        this.realEstatesById = realEstatesById;
    }

    @OneToMany(mappedBy = "userByStaffId")
    public Collection<RealEstate> getRealEstatesById_0() {
        return realEstatesById_0;
    }

    public void setRealEstatesById_0(Collection<RealEstate> realEstatesById_0) {
        this.realEstatesById_0 = realEstatesById_0;
    }

    @OneToMany(mappedBy = "userBySellerId")
    public Collection<Schedule> getSchedulesById() {
        return schedulesById;
    }

    public void setSchedulesById(Collection<Schedule> schedulesById) {
        this.schedulesById = schedulesById;
    }

    @OneToMany(mappedBy = "userByBuyerId")
    public Collection<Transaction> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<Transaction> transactionsById) {
        this.transactionsById = transactionsById;
    }

    @OneToMany(mappedBy = "userBySellerId")
    public Collection<Transaction> getTransactionsById_0() {
        return transactionsById_0;
    }

    public void setTransactionsById_0(Collection<Transaction> transactionsById_0) {
        this.transactionsById_0 = transactionsById_0;
    }

    @OneToMany(mappedBy = "userByStaffId")
    public Collection<Transaction> getTransactionsById_1() {
        return transactionsById_1;
    }

    public void setTransactionsById_1(Collection<Transaction> transactionsById_1) {
        this.transactionsById_1 = transactionsById_1;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    public UserProfile getUserProfileByProfileId() {
        return userProfileByProfileId;
    }

    public void setUserProfileByProfileId(UserProfile userProfileByProfileId) {
        this.userProfileByProfileId = userProfileByProfileId;
    }
}
