package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.model.VehicleType;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeService {
    Result addVehicleType(VehicleType vehicleType);

    DataResult<List<VehicleType>> listVehicleType(Optional<Integer> pagaNum, Optional<Integer> pageSize);

    Result deleteVehicleType(long id);
}
