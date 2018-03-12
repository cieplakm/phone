package com.mmc.phone.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_name")
    private String name;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany( fetch = FetchType.EAGER)
    private List<Phone> phones;


    public User(String name) {
        this.name = name;
        phones = new ArrayList<>();
    }

    public User() {
    }
}
