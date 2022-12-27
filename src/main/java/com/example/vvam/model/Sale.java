package com.example.vvam.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "sale_date")
    private Date saleDate;

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "sale")
    private Collection<Sale_Detail> items;

    public Collection<Sale_Detail> getItems() {
        return items;
    }

    public void setItems(Collection<Sale_Detail> items) {
        this.items = items;
    }

    public Collection<Sale_Detail> getItemsNoEliminados(){
        Collection<Sale_Detail> sale_details = new ArrayList<Sale_Detail>();
        if(this.items!=null){
            Iterator<Sale_Detail> iterator = this.items.iterator();

            while(iterator.hasNext()) {
                Sale_Detail sale_detail = iterator.next();
                if (sale_detail.isEliminado() != true) {
                    sale_details.add(sale_detail);
                }
            }
        }

        return sale_details;
    }

    @Column(name = "eliminado")
    private boolean eliminado;

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Sale() {
    }

    public Sale(Date saleDate, Seller seller, Client client) {
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
        this.eliminado = false;
    }

    public Sale(Long id,Date saleDate, Seller seller, Client client) {
        this.id = id;
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
        this.eliminado = false;
    }
    public Sale(Long id,Date saleDate, Seller seller, Client client, boolean eliminado) {
        this.id = id;
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
        this.eliminado = eliminado;
    }

    public Sale(Date saleDate, Seller seller, Client client, Collection<Sale_Detail> item) {
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
        this.items = item;
        this.eliminado = false;
    }

    public Sale(Long id, Date saleDate, Seller seller, Client client, Collection<Sale_Detail> item) {
        this.id = id;
        this.saleDate = saleDate;
        this.seller = seller;
        this.client = client;
        this.items = item;
        this.eliminado = false;
    }

    public double getTotal(){
        double total = 0;
        Iterator<Sale_Detail> iterator = this.items.iterator();
        while (iterator.hasNext()){
            Sale_Detail current = iterator.next();
            if(current.isEliminado()==false){
                total += current.getSubTotal();
            }

        }
        return total;
    }

    public String getFecha(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(this.saleDate);
    }
}
