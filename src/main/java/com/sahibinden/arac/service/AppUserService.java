package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.model.AppUser;

public interface AppUserService {
    public Result saveAppUser(AppUser appUser);

    long getCustomerIdByEmail(String email);
}
