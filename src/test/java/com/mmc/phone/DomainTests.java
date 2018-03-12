package com.mmc.phone;

import com.mmc.phone.domain.Contact;
import com.mmc.phone.domain.Phone;
import com.mmc.phone.domain.User;
import com.mmc.phone.repos.ContactRepo;
import com.mmc.phone.repos.PhoneRepo;
import com.mmc.phone.repos.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PhoneApplication.class})
public class DomainTests {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PhoneRepo phoneRepo;
    @Autowired
    ContactRepo contactRepo;



    @Before
    public void prepare(){
        //Create and save contact
        Contact contact = new Contact("John", 123456);
        Contact contact2 = new Contact("Ann", 321654);
        Contact contact3 = new Contact("Patt", 654321);

        contactRepo.saveAndFlush(contact);
        contactRepo.saveAndFlush(contact2);
        contactRepo.saveAndFlush(contact3);


        //Create and save Phones
        Phone phone = new Phone("Nokia");
        Phone phone2 = new Phone("Samsung");

        phoneRepo.saveAndFlush(phone);
        phoneRepo.saveAndFlush(phone2);

        //Create and save User
        User user = new User("Matt");

        userRepo.saveAndFlush(user);
    }

    @Test
    public void shouldGetUserPhonesAndContactsSetContactsInPhonesAndSetPhonesInUser(){
        Phone phone = phoneRepo.findById(1L).get();
        Phone phone2 = phoneRepo.findById(2L).get();
        Contact contact = contactRepo.findAll().get(0);
        Contact contact2 = contactRepo.findAll().get(1);
        Contact contact3 = contactRepo.findAll().get(2);


        //Set Contacts for Phones

        phone.getContacts().add(contact);
        phone.getContacts().add(contact2);

        phoneRepo.saveAndFlush(phone);

        phone2.getContacts().add(contact);
        phone2.getContacts().add(contact2);
        phone2.getContacts().add(contact3);

        phoneRepo.saveAndFlush(phone2);


        //Get Phones again to refresh
        phone = phoneRepo.findById(1L).get();
        phone2 = phoneRepo.findById(2L).get();

        User user = userRepo.findAll().get(0);

        user.getPhones().add(phone);
        user = userRepo.saveAndFlush(user);


        User usercheck = userRepo.findAll().get(0);
        User usercheck2 = userRepo.findById(1L).get();


        user = userRepo.findById(1L).get();

        Assertions.assertThat(user.getPhones()).size().isEqualTo(1);
        Assertions.assertThat(user.getPhones().get(0).getContacts()).size().isEqualTo(2);

        //Get User again
        user = userRepo.findById(1L).get();

        user.getPhones().add(phone2);
        user = userRepo.saveAndFlush(user);

        user = userRepo.findById(1L).get();

        Assertions.assertThat(user.getPhones()).size().isEqualTo(2);
        Assertions.assertThat(user.getPhones().get(0).getContacts()).size().isEqualTo(3);
        Assertions.assertThat(user.getPhones().get(1).getContacts()).size().isEqualTo(3);

    }
}
