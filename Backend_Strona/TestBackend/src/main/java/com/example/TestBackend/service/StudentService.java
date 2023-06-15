package com.example.TestBackend.service;

import com.example.TestBackend.exepction.UsernNotFoundException;
import com.example.TestBackend.model.Role;
import com.example.TestBackend.model.Student;
import com.example.TestBackend.repo.RoleRepo;
import com.example.TestBackend.repo.StudentRepo;
import com.example.TestBackend.security.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentService implements UserService, UserDetailsService {
    private final StudentRepo studentRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepo.findStudentByEmail(email);
        if (student == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.error("User found: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        student.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), authorities);
    }

    public Student saveStudent(Student student) {
        boolean studentExists = studentRepo.findByEmail(student.getEmail()).isPresent();


        if (studentExists) {
            throw new IllegalStateException("Email already taken");
        } else {
            if (PasswordValidator.isValid(student.getPassword())) {
                student.setPassword(passwordEncoder.encode(student.getPassword()));
                Role role = roleRepo.findRoleByName("ROLE_USER");
                List<Role> roles = new ArrayList<>();
                roles.add(role);
                student.setRoles(roles);
                return studentRepo.save(student);
            }
            else {
                throw new IllegalStateException("Password don't match pattern");
            }
        }
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        Student student = studentRepo.findStudentByEmail(email);
        Role role = roleRepo.findRoleByName(roleName);
        student.getRoles().add(role);
        studentRepo.save(student);
    }

    @Override
    public Student getStudent(String email) {
        return studentRepo.findStudentByEmail(email);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }


    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student findStudentById(String id) {
        return studentRepo.findStudentById(id).orElseThrow(() -> new UsernNotFoundException("User by id: " + id + "Not found"));
    }

    public void deleteStudent(String id) {
        studentRepo.deleteStudentById(id);
    }


}
