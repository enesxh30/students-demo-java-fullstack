package com.sda.student.mapper;

import com.sda.student.dto.StudentDto;
import com.sda.student.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class StudentMapper {

    private ReviewMapper reviewMapper;
    private CategoryMapper categoryMapper;



    public Student mapToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setUsername(studentDto.getUsername());
        student.setEmail(studentDto.getEmail());
        student.setPassword(studentDto.getPassword());
        student.setAge(studentDto.getAge());
        student.setId(studentDto.getId());
       return student;
    }


    public StudentDto mapToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setUsername(student.getUsername());
        studentDto.setEmail(student.getEmail());
        studentDto.setPassword(student.getPassword());
        studentDto.setAge(student.getAge());
        studentDto.setReviewDtoList(student.getReviews().stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList()));
        studentDto.setCategoryId(student.getCategory().getId());
        studentDto.setCategoryName(student.getCategory().getName());

        return studentDto;
    }
}
