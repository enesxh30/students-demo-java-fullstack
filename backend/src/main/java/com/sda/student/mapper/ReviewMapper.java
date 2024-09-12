package com.sda.student.mapper;

import com.sda.student.dto.ReviewDto;
import com.sda.student.entity.Review;
import com.sda.student.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class ReviewMapper {

//    private StudentMapper studentMapper;



    public Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setName(reviewDto.getName());
        review.setDescription(reviewDto.getDescription());
        return review;
    }

    public ReviewDto mapToDto(Review review) {

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setName(review.getName());
        reviewDto.setDescription(review.getDescription());
//        Student student = review.getStudent();
//        reviewDto.setStudentDto(studentMapper.mapToDto(student));
//        reviewDto.setStudentDto(studentMapper.mapToDto(review.getStudent()));
        return reviewDto;
    }

}
