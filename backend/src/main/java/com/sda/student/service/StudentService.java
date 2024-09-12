package com.sda.student.service;

import com.sda.student.dto.StudentDto;
import com.sda.student.entity.Category;
import com.sda.student.entity.Student;
import com.sda.student.mapper.StudentMapper;
import com.sda.student.repository.CategoryRepository;
import com.sda.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private CategoryRepository categoryRepository;

    public StudentDto save(StudentDto studentDto) {
        Student student = studentMapper.mapToEntity(studentDto);
        Optional<Category> foundCategory = categoryRepository.findById(studentDto.getCategoryId());
        if (foundCategory.isPresent()) {
            student.setCategory(foundCategory.get());
        }
        Student savedStudent = studentRepository.save(student);
        return studentMapper.mapToDto(savedStudent) ;
    }

    public List<StudentDto> findAll() {
        List<Student> studentList = studentRepository.findAll();
//        List<StudentDto> studentDtoList = new ArrayList<>();
//        for (Student student : studentList) {
//            studentDtoList.add(studentMapper.mapToDto(student));
//        }
//        return studentDtoList ;
        return studentList.stream().map(student -> studentMapper.mapToDto(student)).collect(Collectors.toList());
    }

    public StudentDto findById(Long studentId) {
//        Optional<Student> existingStudent = studentRepository .findById(studentId);
//        if (existingStudent.isPresent()){
//            return studentMapper.mapToDto(existingStudent.get());
//        }
//        else throw  new RuntimeException("Student with id: "+studentId+" was not found in the database.");

        Student existingStudent = studentRepository.findById(studentId).orElseThrow(()->
                        new RuntimeException("Student with id: "+studentId+" was not found in the database."));
        return studentMapper.mapToDto(existingStudent);
    }

    public StudentDto update(StudentDto studentDto, Long studentId) {
//        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() ->
//                new RuntimeException("Student not found with ID: " + studentId));
//        existingStudent.setId(studentId);
//        existingStudent.setFirstName(studentDto.getFirstName());
//        existingStudent.setLastName(studentDto.getLastName());
//        existingStudent.setEmail(studentDto.getEmail());
//        existingStudent.setPassword(studentDto.getPassword());
//        existingStudent.setAge(studentDto.getAge());
//        Student savedStudent = studentRepository.save(existingStudent);
//        return studentMapper.mapToDto(savedStudent);

        Optional<Student> existingStudent = studentRepository.findById(studentId);
        Category category = categoryRepository.findById(studentDto.getCategoryId()).orElseThrow(
                ()-> new RuntimeException("Category with id: "+studentDto.getCategoryId()+" not found"));
        if (existingStudent.isPresent()) {

            Student studentToUpdate = existingStudent.get();
            studentToUpdate.setId(studentId);
            studentToUpdate.setFirstName(studentDto.getFirstName());
            studentToUpdate.setLastName(studentDto.getLastName());
            studentToUpdate.setAge(studentDto.getAge());
            studentToUpdate.setEmail(studentDto.getEmail());
            studentToUpdate.setUsername(studentDto.getUsername());
            studentToUpdate.setPassword(studentDto.getPassword());
            studentToUpdate.setCategory(category);

            Student savedStudent = studentRepository.save(studentToUpdate);
            return studentMapper.mapToDto(savedStudent);
        } else {throw new RuntimeException("Student not found with ID: " + studentId);}




    }

    public void delete(Long studentId) {
       Optional<Student> existingStudent = studentRepository.findById(studentId);

       if (existingStudent.isPresent()){
           studentRepository.delete(existingStudent.get());
       }else {throw new RuntimeException("Student not found with ID: " + studentId);}
    }


}
