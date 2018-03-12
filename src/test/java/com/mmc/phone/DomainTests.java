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

    @Test
    public void should(){
        User user = new User("Matt");

        Phone phone = new Phone("Nokia");

        Contact contact = new Contact("John", 123456);
        Contact contact2 = new Contact("Ann", 321654);
        Contact contact3 = new Contact("Patt", 654321);

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);
        user.setPhones(phoneList);

        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        phone.setContacts(contactList);


        user = userRepo.save(user);

        Assertions.assertThat(user.getPhones()).size().isEqualTo(1);
        Assertions.assertThat(user.getPhones().get(0).getContacts()).size().isEqualTo(1);

        contactList.add(contact2);
        contactList.add(contact3);
        phone.setContacts(contactList);

        user = userRepo.save(user);

        Assertions.assertThat(user.getPhones()).size().isEqualTo(1);
        Assertions.assertThat(user.getPhones().get(0).getContacts()).size().isEqualTo(3);


    }

    @Before
    public void prepare(){
        User user = new User("Matt");

        userRepo.save(user);

        Phone phone = new Phone("Nokia");
        Phone phone2 = new Phone("Samsung");

        phoneRepo.save(phone);
        phoneRepo.save(phone2);

        Contact contact = new Contact("John", 123456);
        Contact contact2 = new Contact("Ann", 321654);
        Contact contact3 = new Contact("Patt", 654321);

        contactRepo.save(contact);
        contactRepo.save(contact2);
        contactRepo.save(contact3);
    }

    @Test
    public void should2(){
        User user = userRepo.findAll().get(0);
        Phone phone = phoneRepo.findById(1L).get();
        Phone phone2 = phoneRepo.findById(2L).get();
        Contact contact = contactRepo.findAll().get(0);
        Contact contact2 = contactRepo.findAll().get(1);
        Contact contact3 = contactRepo.findAll().get(2);

        phone.getContacts().add(contact);
        user.getPhones().add(phone);
        user = userRepo.save(user);

        Assertions.assertThat(user.getPhones()).size().isEqualTo(1);
        Assertions.assertThat(user.getPhones().get(0).getContacts()).size().isEqualTo(1);

        phone2.getContacts().add(contact);
        phone2.getContacts().add(contact2);
        phone2.getContacts().add(contact3);

        user.getPhones().add(phone2);

        user = userRepo.save(user);

        Assertions.assertThat(user.getPhones()).size().isEqualTo(2);
        Assertions.assertThat(user.getPhones().get(0).getContacts()).size().isEqualTo(1);
        Assertions.assertThat(user.getPhones().get(1).getContacts()).size().isEqualTo(3);

    }
}
