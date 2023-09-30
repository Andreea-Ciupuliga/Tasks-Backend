package com.example.Tasks.DTOs.TaskDTOs;

import com.example.Tasks.Models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskDTO {
    private Long id;

    private Date dueDate;

    private String subject;

    private Status status;

    private String userFirstName;

    private String userLastName;

    private String username;
}
