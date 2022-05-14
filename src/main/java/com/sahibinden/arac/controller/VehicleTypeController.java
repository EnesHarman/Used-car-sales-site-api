package com.sahibinden.arac.controller;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.requests.FuelTypeAddRequest;
import com.sahibinden.arac.dto.requests.VehicleTypeAddRequest;
import com.sahibinden.arac.model.FuelType;
import com.sahibinden.arac.model.VehicleType;
import com.sahibinden.arac.service.FuelTypeService;
import com.sahibinden.arac.service.VehicleTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicletype")
public class VehicleTypeController {
    private VehicleTypeService vehicleTypeService;

    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVehicleType(@RequestBody VehicleTypeAddRequest vehicleTypeAddRequest) {
        Result result = this.vehicleTypeService.addVehicleType(new VehicleType(vehicleTypeAddRequest.getVehicleType()));
        if(result.getSuccess()){
            return ResponseEntity.ok((result.getMessage()));
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listVehicleType(@RequestParam Optional<Integer> pagaNum, Optional<Integer> pageSize) {
        DataResult<List<VehicleType>> result = this.vehicleTypeService.listVehicleType(pagaNum,pageSize);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteVehicleType(@PathVariable long id) {
        Result result = this.vehicleTypeService.deleteVehicleType(id);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }
}
