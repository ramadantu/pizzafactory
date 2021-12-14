package com.pizzafactory.project.entities;

import javax.persistence.*;

@Entity
@Table(name ="card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String num;

    public Card() {
    }

    public Card(String num, Client client) {
        this.num = num;
        this.client = client;
    }
    public Card(String num){
        this.num=num;
    }


    @OneToOne
    @JoinTable(name = "card_client",
            joinColumns = @JoinColumn(name ="card_id"),
            inverseJoinColumns = @JoinColumn(name="client_id"))
    private Client client;

    public Long getId() {return id;}

    public String getNum() {return num;}

    public void setNum(String num) {this.num = num;}

    public Client getClient() {return client;}

    public void setClient(Client client) {this.client = client;}
}
