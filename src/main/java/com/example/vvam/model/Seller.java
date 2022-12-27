package com.example.vvam.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "dni")
    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Column(name = "first_name")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "active")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    } 

    @OneToMany(mappedBy = "seller")
    private Collection<Sale> sales;

    public Collection<Sale> getSales() {
        return sales;
    }

    public void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }

    public Seller() {
    }

    
    public Seller(String dni, String firstName, String lastName, String email) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = true;
    }

    public Seller(Long id, String dni, String firstName, String lastName, String email, boolean active) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    public Seller(Long id, String dni, String firstName, String lastName, String email, boolean active, Collection<Sale> sales) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.sales = sales;
    }

    public String toJSON(){
        return
                String.format("Seller{id='{0}',dni='{1}',firstName='{2}',lastName='{3}',email='{4}',active='{0}'}", this.id, this.dni, this.firstName, this.lastName, this.email, this.active);
    }

    @Override
    public String toString(){
        return this.firstName + " " + this.lastName;
    }

    public String verActive(boolean active){
        return active==true?"Sí":"No";
    }
}

