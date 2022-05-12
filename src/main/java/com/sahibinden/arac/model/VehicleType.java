package com.sahibinden.arac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_types")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","vehicles"})
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long vehicleTypeId;

    @Column(name = "name", columnDefinition = "varchar(30)")
    private String vehicleTypeName;

    @OneToMany(mappedBy = "type")
    private List<Vehicle> vehicles;

    public VehicleType(long vehicleTypeId, String vehicleTypeName) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
    }
}
