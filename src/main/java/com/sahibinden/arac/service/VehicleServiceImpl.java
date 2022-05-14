package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.*;
import com.sahibinden.arac.dto.requests.VehicleAddRequest;
import com.sahibinden.arac.dto.responses.CompareVehiclesResponse;
import com.sahibinden.arac.dto.responses.SingleVehicleListResponse;
import com.sahibinden.arac.dto.responses.VehicleListResponse;
import com.sahibinden.arac.model.*;
import com.sahibinden.arac.repository.VehicleRepository;
import com.sahibinden.arac.service.constants.Messages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private AppUserService appUserService;
    private PictureService pictureService;
    private CommentService commentService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, AppUserService appUserService, PictureService pictureService, CommentService commentService) {
        this.vehicleRepository = vehicleRepository;
        this.appUserService = appUserService;
        this.pictureService = pictureService;
        this.commentService = commentService;
    }

    @Override
    public Result addVehicle(VehicleAddRequest vehicleAddRequest) {
        long ownerId = appUserService.getCustomerIdByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
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
                .vehiclePrice(vehicleAddRequest.getPrice())
                .isPublished(false)
                .build();
        this.vehicleRepository.save(vehicle);

        List<Picture> pictures = vehicleAddRequest.getPictures();
        this.pictureService.addPictures(pictures, vehicle.getVehicleId());

        return new SuccessResult(Messages.VEHICLE_ADDED);
    }

    @Override
    public DataResult<List<VehicleListResponse>> listVehicles(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        int pagenum = pageNum.isPresent() && pageNum.get() > 0 ? pageNum.get() : 1;
        int pagesize = pageSize.isPresent() && pageSize.get() > 5 && pageSize.get() < 20 ? pageSize.get() : 10;
        Pageable pageable = PageRequest.of(pagenum - 1, pagesize);
        List<VehicleListResponse> vehicleList = this.vehicleRepository.listVehicles(pageable);
        vehicleList.stream().forEach(vehicle -> {
            vehicle.setPictures(this.pictureService.getVehiclePictures(vehicle.getVehicleId()).getData());
        });
        return new SuccessDataResult<List<VehicleListResponse>>(vehicleList);
    }

    @Override
    public DataResult<SingleVehicleListResponse> listSingleVehicle(Optional<Long> id) {
        if (!id.isPresent()) {
            return new ErrorDataResult<>(Messages.VEHICLE_NOT_FOUND);
        }
        SingleVehicleListResponse vehicle = this.vehicleRepository.getSingleVehicle(id.get());
        vehicle.setPictures(this.pictureService.getVehiclePictures(id.get()).getData());
        vehicle.setComments(this.commentService.getVehicleComments(id.get()).getData());
        return new SuccessDataResult<>(vehicle);
    }

    @Override
    @Transactional
    public Result updateVehicle(Optional<Long> id, VehicleAddRequest vehicleUpdateRequest) {
        if (!id.isPresent()) {
            return new ErrorResult(Messages.VEHICLE_NOT_FOUND);
        }
        long ownerId = appUserService.getCustomerIdByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        Vehicle vehicle = this.vehicleRepository.getById(id.get());
        if (vehicle.getOwner().getCustomerId() != ownerId) {
            return new ErrorResult(Messages.NOT_AUTHORIZED_ACTION);
        }

        this.pictureService.updateVehiclePictures(vehicle.getVehicleId(), vehicleUpdateRequest.getPictures());

        vehicle.setVehicleBrand(vehicleUpdateRequest.getVehicleBrand());
        vehicle.setVehicleEngine(vehicleUpdateRequest.getVehicleEngine());
        vehicle.setVehicleModel(vehicleUpdateRequest.getVehicleModel());
        vehicle.setVehicleYear(vehicleUpdateRequest.getVehicleYear());
        vehicle.setCentralLocking(vehicleUpdateRequest.isCentralLocking());
        vehicle.setKilometer(vehicleUpdateRequest.getKilometer());
        vehicle.setFogLight(vehicleUpdateRequest.isFogLight());
        vehicle.setFoldableMirror(vehicleUpdateRequest.isFoldableMirror());
        vehicle.setParkingSensor(vehicleUpdateRequest.isParkingSensor());
        vehicle.setGlassCelling(vehicleUpdateRequest.isGlassCelling());
        vehicle.setType(new VehicleType(vehicleUpdateRequest.getTypeId()));
        vehicle.setFuelType(new FuelType(vehicleUpdateRequest.getFuelTypeId()));
        vehicle.setVehiclePrice(vehicleUpdateRequest.getPrice());
        vehicle.setPublished(false);

        this.vehicleRepository.save(vehicle);
        return new SuccessResult(Messages.VEHICLE_UPDATED);
    }

    @Override
    @Transactional
    public Result publishVehicle(long id) {
        Vehicle vehicle = this.vehicleRepository.getById(id);
        if (vehicle.isPublished()) {
            return new ErrorResult(Messages.PUBLISHED_ALREADY);
        }
        vehicle.setPublished(true);
        this.vehicleRepository.save(vehicle);
        return new SuccessResult(Messages.VEHICLE_PUBLISHED);
    }

    @Override
    public DataResult<List<VehicleListResponse>> listUnPublishedVehicles(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        int pagenum = pageNum.isPresent() && pageNum.get() > 0 ? pageNum.get() : 1;
        int pagesize = pageSize.isPresent() && pageSize.get() > 5 && pageSize.get() < 20 ? pageSize.get() : 10;
        Pageable pageable = PageRequest.of(pagenum - 1, pagesize);
        List<VehicleListResponse> vehicleList = this.vehicleRepository.listUnPublishedVehicles(pageable);
        vehicleList.stream().forEach(vehicle -> {
            vehicle.setPictures(this.pictureService.getVehiclePictures(vehicle.getVehicleId()).getData());
        });
        return new SuccessDataResult<List<VehicleListResponse>>(vehicleList);
    }

    @Override
    @Transactional
    public Result unPublishVehicle(long id) {
        long ownerId = appUserService.getCustomerIdByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        Vehicle vehicle = this.vehicleRepository.getById(id);
        if (vehicle.getOwner().getCustomerId() != ownerId) {
            return new ErrorResult(Messages.NOT_AUTHORIZED_ACTION);
        }
        if (vehicle.isPublished() == false) {
            return new ErrorResult(Messages.UNPUBLISHED_ALREADY);
        }
        vehicle.setPublished(false);
        return new SuccessResult(Messages.UNPUBLISH_VEHICLE);
    }

    @Override
    @Transactional
    public DataResult<CompareVehiclesResponse> compareVehicles(Optional<Long> vehicleIOneId, Optional<Long> vehicleTwoId) {
        if (!vehicleIOneId.isPresent() || !vehicleTwoId.isPresent()) {
            return new ErrorDataResult<>(Messages.VEHICLE_COMPARE_ERROR);
        }
        return new SuccessDataResult<>(
                new CompareVehiclesResponse(
                        this.vehicleRepository.getSingleVehicle(vehicleIOneId.get()),
                        this.vehicleRepository.getSingleVehicle(vehicleTwoId.get())
                )
        );
    }

    @Override
    @Transactional
    public Result deleteVehicle(long id) {
        long ownerId = appUserService.getCustomerIdByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        Vehicle vehicle = this.vehicleRepository.getById(id);
        if (vehicle.getOwner().getCustomerId() != ownerId) {
            return new ErrorResult(Messages.NOT_AUTHORIZED_ACTION);
        }
        this.commentService.deleteVehicleComments(id);
        this.pictureService.deleteVehiclePictures(id);
        this.vehicleRepository.delete(vehicle);
        return new SuccessResult(Messages.VEHICLE_DELETED);
    }
}
