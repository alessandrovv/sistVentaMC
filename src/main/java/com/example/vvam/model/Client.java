package com.example.vvam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
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

    @Column(name = "eliminado")
    private boolean eliminado;

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Collection<Sale> purchases;

    public Collection<Sale> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Sale> purchases) {
        this.purchases = purchases;
    }

    public Client() {
    }

    public Client(Long id, String dni, String firstName, String lastName, String email, boolean active, boolean eliminado) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.eliminado = eliminado;
    }

    public Client(Long id, String dni, String firstName, String lastName, String email) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Client(Long id, boolean active, boolean eliminado) {
        this.id = id;
        this.active = active;
        this.eliminado = eliminado;
    }

    public Client(String dni, String firstName, String lastName, String email) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = true;
        this.eliminado=false;
    }

    public Client(Long id, String dni, String firstName, String lastName, String email, boolean active, boolean eliminado,Collection<Sale> purchases) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.eliminado = eliminado;
        this.purchases = purchases;
    }

    public String toJSON(){
        return
                String.format("Client{id='{0}',dni='{1}',firstName='{2}',lastName='{3}',email='{4}',active='{0}'}", this.id, this.dni, this.firstName, this.lastName, this.email, this.active);
    }

    public String verActive(boolean active){
        return active==true?"Sí":"No";
    }

    @Override
    public String toString() {
        return this.dni + " - " + this.firstName +" "+this.lastName;
    }
}
