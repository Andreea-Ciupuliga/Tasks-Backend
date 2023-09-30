package com.example.Tasks.Models;

import com.example.Tasks.Models.Task;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Transient
    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "user", orphanRemoval = true,cascade = {CascadeType.ALL})
    List<Task> tasks;

}