package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.FuelType;
import com.sahibinden.arac.repository.FuelTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class FuelTypeServiceImpl implements FuelTypeService{
    private FuelTypeRepository fuelTypeRepository;

    public FuelTypeServiceImpl(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public Result addFuelType(FuelType fuelType) {
        this.fuelTypeRepository.save(fuelType);
        return new SuccessResult();
    }
}
