package com.example.avatar.controller;

import com.example.avatar.model.Student;
import com.example.avatar.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return studentService.getById(id);
    }

    @GetMapping("/filtered")
    public Collection<Student> filteredByAge(@RequestParam("age") Integer age) {
        return studentService.getByAge(age);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        studentService.delete(id);
    }
    @GetMapping("/by-age")
    public Collection<Student> filteredByAgeMinMax(@RequestParam int min, @RequestParam int max ) {
        return studentService.getByAgeBetween(min,max);
    }
    @GetMapping("/by-faculty")
    public Collection<Student> findByFaculty(@RequestParam Long facultyId ) {
        return studentService.getByFacultyId(facultyId);
    }
    @GetMapping("/amount")
    public Long getAmountOfStudents(){
        return studentService.getAmountOfStudents();
    }
    @GetMapping("/average-age")
    public Float getAverageAgeOfStudents(){
        return studentService.getAverageAgeOfStudents();
    }
    @GetMapping("/limit-students")
    public List<Student> getLastNumbersOfStudents(){
        return studentService.getLastNumbersOfStudent();
    }
}