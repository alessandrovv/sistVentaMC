package com.example.vvam.dto;

import com.example.vvam.model.*;

public class SaleDetailRegistrationDto {
    private Sale sale;
    private int product;

    private float price;
    private int quantity;
    private boolean eliminado;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public float getPrice(){
        return price;
    }

    public void setPrice(float price){this.price = price;}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public SaleDetailRegistrationDto() {
    }

    public SaleDetailRegistrationDto(int product, float price, int quantity, boolean eliminado) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.eliminado = eliminado;
    }

    public SaleDetailRegistrationDto(Sale sale, int product, float price, int quantity, boolean eliminado) {
        this.sale = sale;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.eliminado = eliminado;
    }
}
