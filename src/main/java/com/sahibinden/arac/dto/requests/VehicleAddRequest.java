package com.sahibinden.arac.dto.requests;

import com.sahibinden.arac.model.*;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class VehicleAddRequest {
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
}
