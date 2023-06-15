package com.example.TestBackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class Student{
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @DocumentReference
    private List<Role> roles;



    public Student(String name, String surname, String email, String password, List<Role> roleList) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roles = roleList;
    }



    @Override
    public String toString(){
        return "Student{" +
                "id" + id +
                "name" + name + '\'' +
                "surname" + surname + '\'' +
                "email" + email + '\'' +
                '}';
    }
}
