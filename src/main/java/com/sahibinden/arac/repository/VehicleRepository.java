package com.sahibinden.arac.repository;

import com.sahibinden.arac.dto.responses.SingleVehicleListResponse;
import com.sahibinden.arac.dto.responses.VehicleListResponse;
import com.sahibinden.arac.model.Vehicle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "select new com.sahibinden.arac.dto.responses.VehicleListResponse(v.vehicleId,v.vehicleYear,v.vehicleBrand," +
            "v.vehicleModel,v.vehicleEngine,v.kilometer,v.fogLight,v.foldableMirror,v.parkingSensor,v.centralLocking," +
            "v.glassCelling,vt.vehicleTypeId,ft.fuelTypeId, v.vehiclePrice) from Vehicle v inner join v.type vt inner join v.fuelType ft")
    List<VehicleListResponse> listVehicles(Pageable pageable);

    @Query(value = "select new com.sahibinden.arac.dto.responses.SingleVehicleListResponse(v.vehicleId,v.vehicleYear,v.vehicleBrand," +
            "v.vehicleModel,v.vehicleEngine,v.kilometer,v.fogLight,v.foldableMirror,v.parkingSensor,v.centralLocking," +
            "v.glassCelling,vt.vehicleTypeId,ft.fuelTypeId, v.vehiclePrice) from Vehicle v inner join v.type vt inner join v.fuelType ft where v.vehicleId=:id")
    SingleVehicleListResponse getSingleVehicle(long id);
}
