package com.example.vvam.model;

import javax.persistence.*;

@Entity
@Table(name = "sale_detail")
public class Sale_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_detail_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Column(name = "producto")
    private int product;

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }


    @Column(name = "precio")
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "quantity")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "eliminado")
    private boolean eliminado;

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Sale_Detail() {
    }

    public Sale_Detail(int product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Sale_Detail(Sale sale, int product, float price , int quantity) {
        this.sale = sale;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.eliminado = false;
    }

    public Sale_Detail(Long id, Sale sale, int product, float price, int quantity ) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.eliminado = false;
    }

    public Sale_Detail(Long id, Sale sale, int product, float price, int quantity, boolean eliminado) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.eliminado = eliminado;
    }

    public double getSubTotal(){
        return this.quantity*this.price;
    }
}
