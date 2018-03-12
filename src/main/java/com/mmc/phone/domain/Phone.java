package com.mmc.phone.domain;

import lombok.Data;

import java.util.List;

@Data
public class Phone {

    private long id;
    private String model;
    private List<Contact> contacts;

}
