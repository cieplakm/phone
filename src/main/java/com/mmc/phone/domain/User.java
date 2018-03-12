package com.mmc.phone.domain;

import lombok.Data;
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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Phone> phones;


    public User(String name) {
        this.name = name;
        phones = new ArrayList<>();
    }

    public User() {
    }
}
