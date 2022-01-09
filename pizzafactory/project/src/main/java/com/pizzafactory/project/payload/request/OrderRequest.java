package com.pizzafactory.project.payload.request;

import com.pizzafactory.project.entities.Client;

public class OrderRequest {

    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
