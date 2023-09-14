package com.example.avatar.service;

import com.example.avatar.model.Student;
import com.example.avatar.model.Student;
import com.example.avatar.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.avatar.Exception.FacultyNotFoundException;
import com.example.avatar.Exception.DataNotFoundedException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getByAge(int age){
        return studentRepository.findAllByAge(age);
    }

    public Student getById(Long id){
        return studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
    }
    public Collection<Student> getAll(){
        return studentRepository.findAll();
    }
    public Student create(Student student){
        return studentRepository.save(student);
    }
    public Student update(Long id, Student student){
        Student existingStudent = studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
        Optional.ofNullable(student.getName()).ifPresent(existingStudent::setName);
//        String name = student.getName();
//        existingStudent.setName(name);
        Optional.of(student.getAge()).ifPresent(existingStudent::setAge);
        return studentRepository.save(existingStudent);
    }
    public void delete(Long id){
        Student existingStudent = studentRepository.findById(id).orElseThrow(DataNotFoundedException::new);
        studentRepository.delete(existingStudent);
    }
    public Collection<Student> getByAgeBetween(int min, int max){
        return studentRepository.findAllByAgeBetween(min,max);
    }
    public Collection<Student> getByFacultyId(Long facultyId){
        return studentRepository.findAllByFacultyId(facultyId);
    }
    public Long getAmountOfStudents(){
        return studentRepository.getAmountOfStudents();
    }
    public Float getAverageAgeOfStudents(){
        return studentRepository.getAverageAgeOfStudents();
    }
    public List<Student> getLastNumbersOfStudent(){
        return studentRepository.getLastNumberOfStudents();
    }
}