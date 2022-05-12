package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.dto.CustomerRegisterRequest;
import com.sahibinden.arac.model.AppUser;
import com.sahibinden.arac.model.Customer;
import com.sahibinden.arac.service.constants.Messages;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService{
    private AppUserService  appUserService;
    private CustomerService customerService;

    public AuthServiceImpl(AppUserService appUserService, CustomerService customerService) {
        this.appUserService = appUserService;
        this.customerService = customerService;
    }

    @Override
    public Result register(CustomerRegisterRequest customerRegisterRequest) {
        AppUser appUser = AppUser.builder()
                .appUserEmail(customerRegisterRequest.getEmail())
                .appUserPassword(customerRegisterRequest.getPassword())
                .appUserName(customerRegisterRequest.getName())
                .appUserSurname(customerRegisterRequest.getSurname())
                .build();
        this.appUserService.saveAppUser(appUser);

        Customer customer = Customer.builder()
                .customerRegisterDate(LocalDateTime.now())
                .customerPhoneNumber(customerRegisterRequest.getPhoneNumber())
                .address(customerRegisterRequest.getAddress())
                .user(appUser)
                .build();

        this.customerService.saveCustomer(customer);
        return new SuccessResult(Messages.CUSTOMER_REGISTERED);
    }
}
