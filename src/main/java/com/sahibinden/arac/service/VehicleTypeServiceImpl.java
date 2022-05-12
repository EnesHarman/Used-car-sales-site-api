package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.VehicleType;
import com.sahibinden.arac.repository.VehicleTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService{
    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public Result addVehicleType(VehicleType vehicleType) {
        this.vehicleTypeRepository.save(vehicleType);
        return new SuccessResult();
    }
}
