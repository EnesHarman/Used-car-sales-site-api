package com.sahibinden.arac.repository;

import com.sahibinden.arac.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
