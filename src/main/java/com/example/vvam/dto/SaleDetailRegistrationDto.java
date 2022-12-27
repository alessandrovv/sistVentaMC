package com.example.vvam.dto;

import com.example.vvam.model.*;

public class SaleDetailRegistrationDto {
    private Sale sale;
    private Product product;
    private int quantity;
    private boolean eliminado;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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

    public SaleDetailRegistrationDto(Product product, int quantity, boolean eliminado) {
        this.product = product;
        this.quantity = quantity;
        this.eliminado = eliminado;
    }

    public SaleDetailRegistrationDto(Sale sale, Product product, int quantity, boolean eliminado) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.eliminado = eliminado;
    }
}
