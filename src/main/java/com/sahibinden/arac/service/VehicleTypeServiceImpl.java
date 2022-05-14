package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessDataResult;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.VehicleType;
import com.sahibinden.arac.repository.VehicleTypeRepository;
import com.sahibinden.arac.service.constants.Messages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public Result addVehicleType(VehicleType vehicleType) {
        this.vehicleTypeRepository.save(vehicleType);
        return new SuccessResult(Messages.VEHICLE_TYPE_ADDED);
    }

    @Override
    public DataResult<List<VehicleType>> listVehicleType(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        int pagenum = pageNum.isPresent() && pageNum.get() > 0 ? pageNum.get() : 1;
        int pagesize = pageSize.isPresent() && pageSize.get() > 5 && pageSize.get() < 20 ? pageSize.get() : 10;
        Pageable pageable = PageRequest.of(pagenum - 1, pagesize);
        return new SuccessDataResult<>(this.vehicleTypeRepository.findAll(pageable).getContent());
    }

    @Override
    public Result deleteVehicleType(long id) {
        this.vehicleTypeRepository.deleteById(id);
        return new SuccessResult(Messages.VEHICLE_TYPE_DELETED);
    }
}
