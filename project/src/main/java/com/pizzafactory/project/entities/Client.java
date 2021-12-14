package com.pizzafactory.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "first_name")
    private String firstName;
    @Column()
    private String lastName;
    @Column()
    private String email;
    @Column()
    private String telNum;
    @Column()
    private String address;
    @Column()
    private String username;
    @Column()
    private String password;
    @Column()
    private Integer bonusPoints;
    @Column()
    private Boolean hasGiftCard;

    @OneToMany(mappedBy = "client" )
    @JsonIgnore
    Set<Orders> order;

    @OneToOne(mappedBy = "client")
    @JsonIgnore
    private Card card;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String telNum, String address, String username, String password,Integer bonusPoints, Boolean hasGiftCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNum = telNum;
        this.address = address;
        this.username = username;
        this.password = password;
        this.bonusPoints=bonusPoints;
        this.hasGiftCard=hasGiftCard;
    }

    public Long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Boolean getHasGiftCard() {
        return hasGiftCard;
    }

    public void setHasGiftCard(Boolean hasGiftCard) {
        this.hasGiftCard = hasGiftCard;
    }

    public Set<Orders> getOrder() {
        return order;
    }

    public void setOrder(Set<Orders> order) {
        this.order = order;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
