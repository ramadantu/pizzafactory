package com.pizzafactory.project.entities.keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClientCardKey implements Serializable {

    private Long clientId;
    private Long cardId;

    public ClientCardKey() {
    }

    public ClientCardKey(Long clientId, Long cardId) {
        this.clientId = clientId;
        this.cardId = cardId;
    }

    public Long getClientId() {return clientId;}

    public void setClientId(Long clientId) {this.clientId = clientId;}

    public Long getCardId() {return cardId;}

    public void setCardId(Long cardId) {this.cardId = cardId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCardKey that = (ClientCardKey) o;
        return clientId.equals(that.clientId) && cardId.equals(that.cardId);
    }

    @Override
    public int hashCode() { return Objects.hash(clientId, cardId); }
}
