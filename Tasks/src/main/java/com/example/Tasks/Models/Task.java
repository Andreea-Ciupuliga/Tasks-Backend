package com.example.Tasks.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "subject", length = 100, nullable = false)
    private String subject;

    @Column(name = "status")
    private Status status;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;
}
