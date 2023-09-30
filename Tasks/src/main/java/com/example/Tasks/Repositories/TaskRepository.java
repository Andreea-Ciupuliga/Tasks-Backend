package com.example.Tasks.Repositories;

import com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO;
import com.example.Tasks.Models.Status;
import com.example.Tasks.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT new com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO(t.id,t.dueDate,t.subject,t.status,u.firstName,u.lastName,u.username) FROM Task t JOIN User u ON u.id=t.user.id WHERE t.id=:id ORDER BY t.dueDate DESC")
    Optional<GetTaskDTO> findTaskById(@Param("id") Long id);
    @Query("SELECT new com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO(t.id,t.dueDate,t.subject,t.status,u.firstName,u.lastName,u.username) FROM Task t JOIN User u ON u.id=t.user.id WHERE t.user.username=:userUsername ORDER BY t.dueDate DESC")
    List<GetTaskDTO> findAllTasksByUserUsername(@Param("userUsername") String userUsername);

    @Query("SELECT new com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO(t.id,t.dueDate,t.subject,t.status,u.firstName,u.lastName,u.username) FROM Task t JOIN User u ON u.id=t.user.id WHERE t.subject LIKE %:text% ORDER BY t.dueDate DESC")
    List<GetTaskDTO> findAllTasksByText(@Param("text") String text);

    @Query("SELECT new com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO(t.id,t.dueDate,t.subject,t.status,u.firstName,u.lastName,u.username) FROM Task t JOIN User u ON u.id=t.user.id WHERE t.status=:status ORDER BY t.dueDate DESC")
    List<GetTaskDTO> findAllTasksByStatus(@Param("status") Status status);

    @Query("SELECT new com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO(t.id,t.dueDate,t.subject,t.status,u.firstName,u.lastName,u.username) FROM Task t JOIN User u ON u.id=t.user.id WHERE t.dueDate > :date ORDER BY t.dueDate DESC")
    List<GetTaskDTO> findAllTasksAfterDate(@Param("date") Date date);

}
