package com.sahibinden.arac.repository;

import com.sahibinden.arac.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
