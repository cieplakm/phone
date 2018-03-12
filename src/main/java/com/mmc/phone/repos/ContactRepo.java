package com.mmc.phone.repos;

import com.mmc.phone.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long>{
}
