package com.mmc.phone.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity

public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_pk")
    private long id;

    @Column(name = "phone_model")
    private String model;

    @ManyToMany(mappedBy = "phones")
    private List<User> phones;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Contact> contacts;

    public Phone(String model) {

        this.model = model;
        contacts = new ArrayList<>();
    }

    public Phone() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        if (!super.equals(o)) return false;
        Phone phone = (Phone) o;
        return id == phone.id &&
                Objects.equals(model, phone.model) &&
                Objects.equals(contacts, phone.contacts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, model, contacts);
    }
}
