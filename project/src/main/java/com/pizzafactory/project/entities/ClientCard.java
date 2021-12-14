package com.pizzafactory.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pizzafactory.project.entities.keys.ClientCardKey;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
public class ClientCard {     //свързочна таблица Loyal
    @EmbeddedId
    private ClientCardKey id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "card_id")
    private Card card;

    public ClientCard() {
    }

    public ClientCard(ClientCardKey id, Client client, Card card) {
        this.id = id;
        this.client = client;
        this.card = card;
    }

    public ClientCardKey getId() {return id;}

    public Client getClient() {return client;}

    public void setClient(Client client) {this.client = client;}

    public Card getCard() {return card;}

    public void setCard(Card card) {this.card = card;}
}
