package com.codefirst.trainee.security.model;

import javax.persistence.*;

@Entity
public class Rolec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    private String name;

    @Column
    private String description;

    public Rolec(String name) {
        this.name = name;
    }
}