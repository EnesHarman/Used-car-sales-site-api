package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.model.Picture;

import java.util.List;

public interface PictureService {
    Result addPictures(List<Picture> picture, long vehicleId);
    DataResult<List<Picture>> getVehiclePictures(long vehicleId);
    Result updateVehiclePictures(long vehicleId, List<Picture> pictures);

    void deleteVehiclePictures(long id);
}
