package com.sahibinden.arac.repository;

import com.sahibinden.arac.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByVehicle_VehicleId(long id);
}
