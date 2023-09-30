package com.example.Tasks.Controllers;

import com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO;
import com.example.Tasks.DTOs.TaskDTOs.GetTaskWithFormattedDateDTO;
import com.example.Tasks.DTOs.TaskDTOs.RegisterTaskDTO;
import com.example.Tasks.Models.Status;
import com.example.Tasks.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/register")
    public void registerTask(@RequestBody RegisterTaskDTO registerTaskDTO) {
        taskService.registerTask(registerTaskDTO);
    }

    @GetMapping("/getAllTasksByUsername/{username}")
    public ResponseEntity<List<GetTaskWithFormattedDateDTO>> getAllTasksByUserUsername(@PathVariable String username) {
        return new ResponseEntity<>(taskService.getAllTasksByUserUsername(username), HttpStatus.OK);
    }

    @GetMapping("/getAllTasksByText/{text}")
    public ResponseEntity<List<GetTaskWithFormattedDateDTO>> getAllTasksByText(@PathVariable String text) {
        return new ResponseEntity<>(taskService.getAllTasksByText(text), HttpStatus.OK);
    }

    @GetMapping("/getAllTasksByStatus/{status}")
    public ResponseEntity<List<GetTaskWithFormattedDateDTO>> getAllTasksByStatus(@PathVariable Status status) {
        return new ResponseEntity<>(taskService.getAllTasksByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/getAllTasksAfterDate/{date}")
    public ResponseEntity<List<GetTaskWithFormattedDateDTO>> getAllTasksAfterDate(@PathVariable Date date) {
        return new ResponseEntity<>(taskService.getAllTasksAfterDate(date), HttpStatus.OK);
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<GetTaskWithFormattedDateDTO> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody RegisterTaskDTO registerTaskDTO) {
        taskService.updateTask(taskId, registerTaskDTO);
    }
}
