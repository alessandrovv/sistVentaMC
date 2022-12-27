package com.example.vvam.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(){

    }

    public Role(String name){
        super();
        this.name = name;
    }

    @Override
    public String toString(){
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
