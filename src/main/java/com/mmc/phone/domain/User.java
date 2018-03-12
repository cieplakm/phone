package com.mmc.phone.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_pk")
    private long id;

    @Column(name = "user_name")
    private String name;


    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Phone> phones;


    public User(String name) {
        this.name = name;
        phones = new ArrayList<>();
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, name, phones);
    }
}
