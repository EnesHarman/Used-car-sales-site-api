package com.sahibinden.arac.service;

import com.sahibinden.arac.model.Manager;
import com.sahibinden.arac.repository.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService{
    private ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public void saveManager(Manager manager) {
        this.managerRepository.save(manager);
    }
}
