package com.sda.student.controller;

import com.sda.student.dto.StudentDto;
import com.sda.student.entity.Student;
import com.sda.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

      private StudentService studentService;


//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public StudentDto save(@Valid @RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

//    @PreAuthorize("hasRole('GUEST')")
    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

//    @PreAuthorize("hasRole('client')")
    @GetMapping("/view/{studentId}")
    public StudentDto findById(@PathVariable(name = "studentId") Long studentId) {
        return studentService.findById(studentId);
    }

    @PutMapping("/{studentId}")
    public StudentDto update(@Valid @RequestBody StudentDto studentDto, @PathVariable(name = "studentId") Long studentId) {
        return studentService.update(studentDto,studentId);
    }


    @DeleteMapping("/{studentId}")
    public String delete(@PathVariable(name = "studentId") Long studentId) {
        studentService.delete(studentId);
        return "Student successfully deleted!";
    }


}
