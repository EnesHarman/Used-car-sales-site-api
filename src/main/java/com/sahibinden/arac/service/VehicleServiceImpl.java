package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.dto.VehicleAddRequest;
import com.sahibinden.arac.model.*;
import com.sahibinden.arac.repository.VehicleRepository;
import com.sahibinden.arac.service.constants.Messages;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    private VehicleRepository vehicleRepository;
    private AppUserService appUserService;
    private PictureService pictureService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, AppUserService appUserService, PictureService pictureService) {
        this.vehicleRepository = vehicleRepository;
        this.appUserService = appUserService;
        this.pictureService = pictureService;
    }

    @Override
    public Result addVehicle(VehicleAddRequest vehicleAddRequest) {
        long ownerId = appUserService.getAppUserIdByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Customer customer = new Customer(ownerId);

        Vehicle vehicle = Vehicle.builder()
                .vehicleYear(vehicleAddRequest.getVehicleYear())
                .vehicleBrand(vehicleAddRequest.getVehicleBrand())
                .vehicleModel(vehicleAddRequest.getVehicleModel())
                .vehicleEngine(vehicleAddRequest.getVehicleEngine())
                .kilometer(vehicleAddRequest.getKilometer())
                .fogLight(vehicleAddRequest.isFogLight())
                .parkingSensor(vehicleAddRequest.isParkingSensor())
                .centralLocking(vehicleAddRequest.isCentralLocking())
                .glassCelling(vehicleAddRequest.isGlassCelling())
                .type(new VehicleType(vehicleAddRequest.getTypeId()))
                .fuelType(new FuelType(vehicleAddRequest.getFuelTypeId()))
                .owner(customer)
                .build();
        this.vehicleRepository.save(vehicle);

        List<Picture> pictures = vehicleAddRequest.getPictures();
        this.pictureService.addPictures(pictures, vehicle.getVehicleId());

        return new SuccessResult(Messages.VEHICLE_ADDED);
    }
}
