package com.sahibinden.arac.repository;

import com.sahibinden.arac.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
