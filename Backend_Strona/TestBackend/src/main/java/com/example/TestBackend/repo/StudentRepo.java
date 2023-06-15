package com.example.TestBackend.repo;

import com.example.TestBackend.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends MongoRepository<Student, String> {

    void deleteStudentById(String id);

    Optional<Student> findStudentById(String id);

    Student findStudentByEmail(String email);

    Optional<Student> findByEmail(String email);
}
