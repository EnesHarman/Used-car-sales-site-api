package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.Role;
import com.sahibinden.arac.repository.RoleRepository;
import com.sahibinden.arac.service.constants.Messages;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Result addRole(Role role) {
        this.roleRepository.save(role);
        return new SuccessResult(Messages.ROLE_ADDED);
    }
}
