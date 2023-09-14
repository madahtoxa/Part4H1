package com.example.avatar.repository;

import com.example.avatar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllByAge(int age);

    List<Student>findAllByAgeBetween(int min,int max);

    List<Student> findAllByFacultyId(Long facultyId);
    @Query(value = "SELECT COUNT(*) FROM STUDENT",nativeQuery = true)
    Long getAmountOfStudents();
    @Query(value = "SELECT AVG(age) FROM STUDENT",nativeQuery = true)
    Float getAverageAgeOfStudents();
    @Query(value = "SELECT * FROM STUDENT LIMIT 2",nativeQuery = true)
    List<Student> getLastNumberOfStudents();

}

