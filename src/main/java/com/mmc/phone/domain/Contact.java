package com.mmc.phone.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "contact_name")
    private String name;

    private int number;

    public Contact(String name, int number) {

        this.name = name;
        this.number = number;
    }

    public Contact() {
    }
}
