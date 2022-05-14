package com.sahibinden.arac.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareVehiclesResponse {
    private SingleVehicleListResponse vehicle1;
    private SingleVehicleListResponse vehicle2;
}
