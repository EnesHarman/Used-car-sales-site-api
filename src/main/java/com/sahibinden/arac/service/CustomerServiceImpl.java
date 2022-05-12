package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.Customer;
import com.sahibinden.arac.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Result saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
        return new SuccessResult();
    }
}
