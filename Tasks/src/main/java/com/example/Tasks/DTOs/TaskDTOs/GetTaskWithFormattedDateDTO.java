package com.example.Tasks.DTOs.TaskDTOs;

import com.example.Tasks.Models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskWithFormattedDateDTO {
    private Long id;

    private String dueDate;

    private String subject;

    private Status status;

    private String userFirstName;

    private String userLastName;

    private String username;
}
