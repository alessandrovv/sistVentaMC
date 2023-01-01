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


    @Column(name = "documentoIdentidad")
    private String documentoIdentidad;

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    @Column(name = "razonSocial")
    private String razonSocial;

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    public Client(Long id, String documentoIdentidad, String razonSocial, String email, boolean active, boolean eliminado) {
        this.id = id;
        this.documentoIdentidad = documentoIdentidad;
        this.razonSocial = razonSocial;

        this.email = email;
        this.active = active;
        this.eliminado = eliminado;
    }

    public Client(Long id, String documentoIdentidad, String razonSocial, String email) {
        this.id = id;
        this.documentoIdentidad = documentoIdentidad;
        this.razonSocial = razonSocial;
        this.email = email;
        this.active=true;
        this.eliminado = false;
    }

    public Client(Long id, boolean active, boolean eliminado) {
        this.id = id;
        this.active = active;
        this.eliminado = eliminado;
    }

    public Client(String documentoIdentidad, String razonSocial, String email) {
        this.documentoIdentidad = documentoIdentidad;
        this.razonSocial = razonSocial;
        this.email = email;
        this.active = true;
        this.eliminado=false;
    }

    public Client(Long id, String documentoIdentidad, String razonSocial, String email, boolean active, boolean eliminado,Collection<Sale> purchases) {
        this.id = id;
        this.documentoIdentidad = documentoIdentidad;
        this.razonSocial = razonSocial;
        this.email = email;
        this.active = active;
        this.eliminado = eliminado;
        this.purchases = purchases;
    }

    public String toJSON(){
        return
                String.format("Client{id='{0}',documentoIdentidad='{1}',razonSocial='{2}',email='{4}',active='{0}'}", this.id, this.documentoIdentidad, this.razonSocial, this.email, this.active);
    }

    public String verActive(boolean active){
        return active==true?"Sí":"No";
    }

    @Override
    public String toString() {
        return this.razonSocial;
    }

    public String toString1() {return this.documentoIdentidad + " - " + this.razonSocial;}
}
