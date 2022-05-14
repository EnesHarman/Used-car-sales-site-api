package com.sahibinden.arac.dto.responses;

import com.sahibinden.arac.model.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleListResponse {
    private long vehicleId;

    private LocalDate vehicleYear;

    private String vehicleBrand;

    private String vehicleModel;

    private String vehicleEngine;

    private int kilometer;

    private boolean fogLight;

    private boolean foldableMirror;

    private boolean parkingSensor;

    private boolean centralLocking;

    private boolean glassCelling;

    private List<Picture> pictures;

    private long typeId;

    private long fuelTypeId;

    private long price;

    public VehicleListResponse(long vehicleId, LocalDate vehicleYear, String vehicleBrand, String vehicleModel, String vehicleEngine, int kilometer, boolean fogLight, boolean foldableMirror, boolean parkingSensor, boolean centralLocking, boolean glassCelling, long typeId, long fuelTypeId, long price) {
        this.vehicleId = vehicleId;
        this.vehicleYear = vehicleYear;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleEngine = vehicleEngine;
        this.kilometer = kilometer;
        this.fogLight = fogLight;
        this.foldableMirror = foldableMirror;
        this.parkingSensor = parkingSensor;
        this.centralLocking = centralLocking;
        this.glassCelling = glassCelling;
        this.typeId = typeId;
        this.fuelTypeId = fuelTypeId;
        this.price =   price;
    }
}
