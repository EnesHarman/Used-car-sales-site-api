package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.core.result.SuccessDataResult;
import com.sahibinden.arac.core.result.SuccessResult;
import com.sahibinden.arac.model.Picture;
import com.sahibinden.arac.model.Vehicle;
import com.sahibinden.arac.repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService{
    private PictureRepository pictureRepository;
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Result addPictures(List<Picture> pictures, long vehicleId) {
        pictures.stream().forEach(picture -> {
            picture.setVehicle(new Vehicle(vehicleId));
            this.pictureRepository.save(picture);
        });
        return new SuccessResult();
    }

    @Override
    public DataResult<List<Picture>> getVehiclePictures(long vehicleId) {
        return new SuccessDataResult<>(this.pictureRepository.findAllByVehicle_VehicleId(vehicleId));
    }
}
