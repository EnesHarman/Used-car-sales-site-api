package com.sahibinden.arac.repository;

import com.sahibinden.arac.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByAppUserEmail(String email);
}
