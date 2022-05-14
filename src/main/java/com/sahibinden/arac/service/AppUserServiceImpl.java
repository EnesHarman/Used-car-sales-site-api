package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.AppUser;
import com.sahibinden.arac.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findByAppUserEmail(email);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found."); //TODO GÜZELLEŞTİR
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(appUser.getRole().getRoleName()));

        return new User(appUser.getAppUserEmail(), appUser.getAppUserPassword(), authorities);
    }

    @Override
    public Result saveAppUser(AppUser appUser) {
        appUser.setAppUserPassword(passwordEncoder.encode(appUser.getAppUserPassword()));
        this.appUserRepository.save(appUser);
        return new SuccessResult();
    }

    @Override
    public long getCustomerIdByEmail(String email) {
        return this.appUserRepository.findByAppUserEmail(email).getCustomer().getCustomerId();
    }
}
