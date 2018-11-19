package com.abc.restapi.controller;


import com.abc.restapi.exception.ResourceNotFoundException;
import com.abc.restapi.model.Student;
import com.abc.restapi.repository.ModuleRepo;
import com.abc.restapi.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/abcStudent")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public List<Student> getAllStudentss() {
        return studentRepo.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable(value = "id") int studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
    }

    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepo.save(student);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable(value = "id") int studentId,
                           @Valid @RequestBody Student studentDetials) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        student.setStudentName(studentDetials.getStudentName());
        student.setStudentAge(studentDetials.getStudentAge());

        Student updatedStudent = studentRepo.save(student);
        return updatedStudent;
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") int studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        studentRepo.delete(student);

        return ResponseEntity.ok().build();
    }

}
