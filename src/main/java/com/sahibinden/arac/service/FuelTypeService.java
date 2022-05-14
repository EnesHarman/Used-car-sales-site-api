package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.model.FuelType;

import java.util.List;
import java.util.Optional;

public interface FuelTypeService {
    Result addFuelType(FuelType fuelType);

    DataResult<List<FuelType>> listFuelTypes(Optional<Integer> pagaNum, Optional<Integer> pageSize);

    Result deleteFuelType(long id);
}
