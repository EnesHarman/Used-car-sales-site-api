package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.model.Customer;

public interface CustomerService {
    Result saveCustomer(Customer customer);
}
