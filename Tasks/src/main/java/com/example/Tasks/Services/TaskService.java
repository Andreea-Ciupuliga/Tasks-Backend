package com.example.Tasks.Services;

import com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO;
import com.example.Tasks.DTOs.TaskDTOs.GetTaskWithFormattedDateDTO;
import com.example.Tasks.DTOs.TaskDTOs.RegisterTaskDTO;
import com.example.Tasks.DTOs.UserDTOs.GetUserDTO;
import com.example.Tasks.Exceptions.NotFoundException;
import com.example.Tasks.Models.Status;
import com.example.Tasks.Models.Task;
import com.example.Tasks.Models.User;
import com.example.Tasks.Repositories.TaskRepository;
import com.example.Tasks.Repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;


    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public void registerTask(RegisterTaskDTO registerTaskDTO) {

        GetUserDTO userDTO = userRepository.findByUsername(registerTaskDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));

        Task task = Task.builder()
                .dueDate(registerTaskDTO.getDueDate())
                .subject(registerTaskDTO.getSubject())
                .status(registerTaskDTO.getStatus())
                .user(User.builder().id(userDTO.getId()).build())
                .build();

        taskRepository.save(task);
    }

    @SneakyThrows
    public void updateTask(Long taskId, RegisterTaskDTO registerTaskDTO) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));


        if (registerTaskDTO.getDueDate() != null) {
            task.setDueDate(registerTaskDTO.getDueDate());
        }
        if (registerTaskDTO.getSubject() != "")
            task.setSubject(registerTaskDTO.getSubject());

        if (registerTaskDTO.getStatus() != null)
            task.setStatus(registerTaskDTO.getStatus());

        if (registerTaskDTO.getUsername() != "") {
            GetUserDTO userDTO = userRepository.findByUsername(registerTaskDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
            User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new NotFoundException("User not found"));

            task.setUser(user);
        }

        taskRepository.save(task);
    }

    @SneakyThrows
    public List<GetTaskWithFormattedDateDTO> getAllTasksByUserUsername(String username) {

        if (userRepository.findByUsername(username).isEmpty())
            throw new NotFoundException("Student not found");

        List<GetTaskDTO> tasks = taskRepository.findAllTasksByUserUsername(username); // lista de taskuri cum vin din baza de date
        List<GetTaskWithFormattedDateDTO> formattedTasks = new ArrayList<>(); // viitoare lista de taskuri care va fi ca cea din baza de date doar ca data va fi formatata sub forma (dd.mm.yyyy)

        DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (GetTaskDTO task : tasks) {

            String formattedDate = targetFormat.format(task.getDueDate()); //aici avem data formatata dar sub forma de string ptc daca o lasam Date aveam afisat si timpul

            //Creez un ofiect task care sa aiba data ca string si nu ca Date
            GetTaskWithFormattedDateDTO taskWithFormattedDateDTO = GetTaskWithFormattedDateDTO.builder()
                    .id(task.getId())
                    .dueDate(formattedDate)
                    .subject(task.getSubject())
                    .status(task.getStatus())
                    .userFirstName(task.getUserFirstName())
                    .userLastName(task.getUserLastName())
                    .username(task.getUsername())
                    .build();

            formattedTasks.add(taskWithFormattedDateDTO);
        }

        return formattedTasks;
    }


    @SneakyThrows
    public List<GetTaskWithFormattedDateDTO> getAllTasksByText(String text) {

        List<GetTaskDTO> tasks = taskRepository.findAllTasksByText(text); // lista de taskuri cum vin din baza de date
        List<GetTaskWithFormattedDateDTO> formattedTasks = new ArrayList<>(); // viitoare lista de taskuri care va fi ca cea din baza de date doar ca data va fi formatata sub forma (dd.mm.yyyy)

        DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (GetTaskDTO task : tasks) {

            String formattedDate = targetFormat.format(task.getDueDate()); //aici avem data formatata dar sub forma de string ptc daca o lasam Date aveam afisat si timpul

            //Creez un ofiect task care sa aiba data ca string si nu ca Date
            GetTaskWithFormattedDateDTO getTaskWithFormattedDateDTO = GetTaskWithFormattedDateDTO.builder()
                    .id(task.getId())
                    .dueDate(formattedDate)
                    .subject(task.getSubject())
                    .status(task.getStatus())
                    .userFirstName(task.getUserFirstName())
                    .userLastName(task.getUserLastName())
                    .username(task.getUsername())
                    .build();

            formattedTasks.add(getTaskWithFormattedDateDTO);
        }

        return formattedTasks;
    }

    @SneakyThrows
    public List<GetTaskWithFormattedDateDTO> getAllTasksAfterDate(Date date) {

        List<GetTaskDTO> tasks = taskRepository.findAllTasksAfterDate(date); // lista de taskuri cum vin din baza de date
        List<GetTaskWithFormattedDateDTO> formattedTasks = new ArrayList<>(); // viitoare lista de taskuri care va fi ca cea din baza de date doar ca data va fi formatata sub forma (dd.mm.yyyy)

        DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (GetTaskDTO task : tasks) {

            String formattedDate = targetFormat.format(task.getDueDate()); //aici avem data formatata dar sub forma de string ptc daca o lasam Date aveam afisat si timpul

            //Creez un ofiect task care sa aiba data ca string si nu ca Date
            GetTaskWithFormattedDateDTO getTaskWithFormattedDateDTO = GetTaskWithFormattedDateDTO.builder()
                    .id(task.getId())
                    .dueDate(formattedDate)
                    .subject(task.getSubject())
                    .status(task.getStatus())
                    .userFirstName(task.getUserFirstName())
                    .userLastName(task.getUserLastName())
                    .username(task.getUsername())
                    .build();

            formattedTasks.add(getTaskWithFormattedDateDTO);
        }

        return formattedTasks;
    }

    @SneakyThrows
    public List<GetTaskWithFormattedDateDTO> getAllTasksByStatus(Status status) {

        List<GetTaskDTO> tasks = taskRepository.findAllTasksByStatus(status); // lista de taskuri cum vin din baza de date
        List<GetTaskWithFormattedDateDTO> formattedTasks = new ArrayList<>(); // viitoare lista de taskuri care va fi ca cea din baza de date doar ca data va fi formatata sub forma (dd.mm.yyyy)

        DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (GetTaskDTO task : tasks) {

            String formattedDate = targetFormat.format(task.getDueDate()); //aici avem data formatata dar sub forma de string ptc daca o lasam Date aveam afisat si timpul

            //Creez un ofiect task care sa aiba data ca string si nu ca Date
            GetTaskWithFormattedDateDTO getTaskWithFormattedDateDTO = GetTaskWithFormattedDateDTO.builder()
                    .id(task.getId())
                    .dueDate(formattedDate)
                    .subject(task.getSubject())
                    .status(task.getStatus())
                    .userFirstName(task.getUserFirstName())
                    .userLastName(task.getUserLastName())
                    .username(task.getUsername())
                    .build();

            formattedTasks.add(getTaskWithFormattedDateDTO);
        }

        return formattedTasks;
    }


    @SneakyThrows
    public GetTaskWithFormattedDateDTO getTaskById(Long id) {

        GetTaskDTO task = taskRepository.findTaskById(id).orElseThrow(() -> new NotFoundException("Task not found"));

        DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = targetFormat.format(task.getDueDate()); //aici avem data formatata dar sub forma de string ptc daca o lasam Date aveam afisat si timpul

        //Creez un obiect task care sa aiba data ca string si nu ca Date
        GetTaskWithFormattedDateDTO getTaskWithFormattedDateDTO = GetTaskWithFormattedDateDTO.builder()
                .id(task.getId())
                .dueDate(formattedDate)
                .subject(task.getSubject())
                .status(task.getStatus())
                .userFirstName(task.getUserFirstName())
                .userLastName(task.getUserLastName())
                .username(task.getUsername())
                .build();

        return getTaskWithFormattedDateDTO;
    }


}
