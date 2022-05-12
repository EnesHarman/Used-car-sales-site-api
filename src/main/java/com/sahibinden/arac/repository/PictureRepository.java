package com.sahibinden.arac.repository;

import com.sahibinden.arac.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture,Long> {
    List<Picture> findAllByVehicle_VehicleId(long vehicleId);
}
