package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.CustomerRegisterRequest;

public interface AuthService {
    Result register(CustomerRegisterRequest customerRegisterRequest);
}
