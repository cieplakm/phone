package com.mmc.phone.repos;

import com.mmc.phone.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepo extends JpaRepository<Phone, Long> {
}
