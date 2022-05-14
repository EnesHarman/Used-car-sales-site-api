package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessDataResult;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.FuelType;
import com.sahibinden.arac.repository.FuelTypeRepository;
import com.sahibinden.arac.service.constants.Messages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelTypeServiceImpl implements FuelTypeService{
    private FuelTypeRepository fuelTypeRepository;

    public FuelTypeServiceImpl(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public Result addFuelType(FuelType fuelType) {
        this.fuelTypeRepository.save(fuelType);
        return new SuccessResult(Messages.FUEL_TYPE_ADDED);
    }

    @Override
    public DataResult<List<FuelType>> listFuelTypes(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        int pagenum = pageNum.isPresent() && pageNum.get() > 0 ? pageNum.get() : 1;
        int pagesize = pageSize.isPresent() && pageSize.get() > 5 && pageSize.get() < 20 ? pageSize.get() : 10;
        Pageable pageable = PageRequest.of(pagenum - 1, pagesize);
        List<FuelType> fuelTypes = this.fuelTypeRepository.findAll(pageable).getContent();
        return new SuccessDataResult<>(fuelTypes);
    }

    @Override
    public Result deleteFuelType(long id) {
        this.fuelTypeRepository.deleteById(id);
        return new SuccessResult(Messages.FUEL_TYPE_DELETED);
    }
}
