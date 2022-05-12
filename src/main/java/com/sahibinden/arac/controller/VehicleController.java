package com.sahibinden.arac.controller;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.VehicleAddRequest;
import com.sahibinden.arac.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleAddRequest vehicleAddRequest){
        Result result = this.vehicleService.addVehicle(vehicleAddRequest);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }

}
