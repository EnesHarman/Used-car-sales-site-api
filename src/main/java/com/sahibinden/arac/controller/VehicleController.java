package com.sahibinden.arac.controller;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.requests.VehicleAddRequest;
import com.sahibinden.arac.dto.responses.SingleVehicleListResponse;
import com.sahibinden.arac.dto.responses.VehicleListResponse;
import com.sahibinden.arac.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleAddRequest vehicleAddRequest) {
        Result result = this.vehicleService.addVehicle(vehicleAddRequest);
        if (result.getSuccess()) {
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listVehicle(@PathVariable Optional<Long> id, @RequestParam Optional<Integer> pagaNum, Optional<Integer> pageSize) {
        DataResult<List<VehicleListResponse>> result = this.vehicleService.listVehicles(pagaNum, pageSize);
        if (result.getSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> listSingleVehicle(@PathVariable Optional<Long> id) {
        DataResult<SingleVehicleListResponse> result = this.vehicleService.listSingleVehicle(id);
        if (result.getSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable Optional<Long> id, @RequestBody VehicleAddRequest vehicleUpdateRequest) {
        Result result = this.vehicleService.updateVehicle(id, vehicleUpdateRequest);
        if (result.getSuccess()) {
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<String> publishVehicle(@PathVariable long id) {
        Result result = this.vehicleService.publishVehicle(id);
        if (result.getSuccess()) {
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @PutMapping("/unpublish/{id}")
    public ResponseEntity<String> unPublishVehicle(@PathVariable long id) {
        Result result = this.vehicleService.unPublishVehicle(id);
        if (result.getSuccess()) {
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @GetMapping("unpublished/list")
    public ResponseEntity<Object> listUnPublishedVehicles(@RequestParam Optional<Integer> pagaNum, Optional<Integer> pageSize) {
        DataResult<List<VehicleListResponse>> result = this.vehicleService.listUnPublishedVehicles(pagaNum, pageSize);
        if (result.getSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }


}
