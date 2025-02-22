package com.sda.student.repository;

import com.sda.student.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByStudentId(long studentId);

}
