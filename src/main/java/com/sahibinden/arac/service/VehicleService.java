package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.VehicleAddRequest;

public interface VehicleService {
    Result addVehicle(VehicleAddRequest vehicleAddRequest);
}
