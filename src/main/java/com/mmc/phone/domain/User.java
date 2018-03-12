package com.mmc.phone.domain;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private long id;
    private String name;
    private List<Phone> phones;

}
