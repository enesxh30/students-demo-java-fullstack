package com.sda.student.controller;

import com.sda.student.dto.ReviewDto;
import com.sda.student.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/save/{studentId}")
    public ResponseEntity<ReviewDto> save(@RequestBody ReviewDto reviewDto, @PathVariable("studentId") long studentId) {
        return new ResponseEntity<>(reviewService.save(reviewDto,studentId), HttpStatus.CREATED);
    }
    //@PreAuthorize("hasRole('INTERN')")
    @GetMapping("/{studentId}")
    public ResponseEntity<List<ReviewDto>> findAll(@PathVariable("studentId") long studentId) {
        return new ResponseEntity<>(reviewService.findAll(studentId),HttpStatus.OK);
    }

    @GetMapping("/{studentId}/{reviewId}")
    public ResponseEntity<ReviewDto> findById(@PathVariable("studentId") long studentId,
                                              @PathVariable("reviewId") long reviewId) {
        return ResponseEntity.ok(reviewService.findById(studentId,reviewId));
    }

    @PutMapping("/{studentId}/{reviewId}")
    public ResponseEntity<ReviewDto> update(@RequestBody ReviewDto reviewDto,
                                            @PathVariable("studentId") long studentId,
                                            @PathVariable("reviewId") long reviewId) {
        return ResponseEntity.ok(reviewService.update(studentId,reviewId,reviewDto));
    }


    public ResponseEntity<String> delete(@PathVariable("studentId") long studentId,
                                         @PathVariable("reviewId") long reviewId) {
        reviewService.delete(studentId,reviewId);
        return ResponseEntity.ok("Review successfully deleted");
    }
}
