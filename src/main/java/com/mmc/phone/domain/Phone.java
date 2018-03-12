package com.mmc.phone.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "phone_model")
    private String model;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Contact> contacts;

    public Phone(String model) {

        this.model = model;
        contacts = new ArrayList<>();
    }

    public Phone() {
    }
}
