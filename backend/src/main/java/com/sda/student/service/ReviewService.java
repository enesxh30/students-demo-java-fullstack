package com.sda.student.service;

import com.sda.student.dto.ReviewDto;
import com.sda.student.entity.Review;
import com.sda.student.entity.Student;
import com.sda.student.mapper.ReviewMapper;
import com.sda.student.repository.ReviewRepository;
import com.sda.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private StudentRepository studentRepository;
    private ReviewMapper reviewMapper;

    public ReviewDto save(ReviewDto reviewDto,long studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(()->
                new RuntimeException("Student with id: "+studentId+" not found"));

        Review review = reviewMapper.mapToEntity(reviewDto);
        review.setStudent(existingStudent);
        Review savedReview = reviewRepository.save(review);

        return reviewMapper.mapToDto(savedReview);
    }


    public List<ReviewDto> findAll(long studentId) {
        List<Review> reviewList = reviewRepository.findByStudentId(studentId);
//        List<ReviewDto> reviewDtoList= new ArrayList<>();
//        for (Review review: reviewList) {
//            reviewDtoList.add(reviewMapper.mapToDto(review));
//        }
//        return  reviewDtoList;
        return reviewList.stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList());
    }


    public ReviewDto findById(long studentId, long reviewId) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(
                ()->new RuntimeException("Student with id: "+studentId+" not found"));

        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new RuntimeException("Review with id: "+reviewId+" not found"));

        if (!((existingStudent.getId())==(existingReview.getStudent().getId()))) {
            throw new RuntimeException("Review with id :"+reviewId+"doesn't correspond to student with id: "+studentId);
        }



        return reviewMapper.mapToDto(existingReview);
    }


    public ReviewDto update(long studentId, long reviewId, ReviewDto reviewDto) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(
                ()->new RuntimeException("Student with id: "+studentId+" not found"));

        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new RuntimeException("Review with id: "+reviewId+" not found"));

        if (!((existingStudent.getId())==(existingReview.getStudent().getId()))) {
            throw new RuntimeException("Review with id :"+reviewId+"doesn't correspond to student with id: "+studentId);
        }

        existingReview.setId(reviewId);
        existingReview.setName(reviewDto.getName());
        existingReview.setDescription(reviewDto.getDescription());
        existingReview.setStudent(existingStudent);
        Review savedReview = reviewRepository.save(existingReview);

        return reviewMapper.mapToDto(savedReview);

    }

    public void delete(long studentId, long reviewId) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(
                ()->new RuntimeException("Student with id: "+studentId+" not found"));

        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new RuntimeException("Review with id: "+reviewId+" not found"));

        if (!((existingStudent.getId())==(existingReview.getStudent().getId()))) {
            throw new RuntimeException("Review with id :"+reviewId+"doesn't correspond to student with id: "+studentId);
        }

        reviewRepository.delete(existingReview);
    }

}
