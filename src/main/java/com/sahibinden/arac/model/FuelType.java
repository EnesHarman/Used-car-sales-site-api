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
@Table(name = "fuel_types")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "vehicles"})
public class FuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long fuelTypeId;

    @Column(name = "name", columnDefinition = "varchar(30)")
    private String fuelTypeName;

    @OneToMany(mappedBy = "fuelType")
    private List<Vehicle> vehicles;

    public FuelType(long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public FuelType(long fuelTypeId, String fuelTypeName) {
        this.fuelTypeId = fuelTypeId;
        this.fuelTypeName = fuelTypeName;
    }
}
