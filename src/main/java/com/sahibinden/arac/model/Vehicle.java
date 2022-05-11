package com.sahibinden.arac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long vehicleId;

    @Column(name = "year")
    private LocalDate vehicleYear;

    @Column(name = "brand", columnDefinition = "varchar(50)")
    private String vehicleBrand;

    @Column(name = "model",columnDefinition = "varchar(50)")
    private String vehicleModel;

    @Column(name = "engine", columnDefinition = "varchar(50)")
    private String vehicleEngine;

    @Column(name = "kilometer")
    private int kilometer;

    @Column(name = "fog_light")
    private boolean fogLight;

    @Column(name = "foldable_mirror")
    private boolean foldableMirror;

    @Column(name = "parking_sensor")
    private boolean parkingSensor;

    @Column(name = "central_locking")
    private boolean centralLocking;

    @Column(name = "glass_celling")
    private boolean glassCelling;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private VehicleType type;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
