package com.example.vvam.dto;

import com.example.vvam.model.*;

import java.util.Date;

public class SaleRegistrationDto {
    private Client client;
    private Seller seller;
    private Date date;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SaleRegistrationDto() {
    }

    public SaleRegistrationDto(Client client, Seller seller, Date date) {
        this.client = client;
        this.seller = seller;
        this.date = date;
    }
}
