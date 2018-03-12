package com.mmc.phone.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_pk")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        if (!super.equals(o)) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                number == contact.number &&
                Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, name, number);
    }
}
