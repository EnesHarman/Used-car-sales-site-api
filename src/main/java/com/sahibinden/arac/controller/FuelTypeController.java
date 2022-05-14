package com.sahibinden.arac.controller;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.requests.FuelTypeAddRequest;
import com.sahibinden.arac.model.FuelType;
import com.sahibinden.arac.service.FuelTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fueltype")
public class FuelTypeController {
    private FuelTypeService fuelTypeService;

    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addFuelType(@RequestBody FuelTypeAddRequest fuelTypeAddRequest) {
        Result result = this.fuelTypeService.addFuelType(new FuelType(fuelTypeAddRequest.getFuelType()));
        if(result.getSuccess()){
            return ResponseEntity.ok((result.getMessage()));
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listFuelTypes(@RequestParam Optional<Integer> pagaNum, Optional<Integer> pageSize) {
        DataResult<List<FuelType>> result = this.fuelTypeService.listFuelTypes(pagaNum,pageSize);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFuelType(@PathVariable long id) {
        Result result = this.fuelTypeService.deleteFuelType(id);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }
}
