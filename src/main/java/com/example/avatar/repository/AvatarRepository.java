package com.example.avatar.repository;
import com.example.avatar.model.Avatar;
import com.example.avatar.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudent(Student student);

}