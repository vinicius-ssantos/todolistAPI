package com.vinicius_santos.todolist.service.implementation;

import com.vinicius_santos.todolist.dto.TaskDTO;
import com.vinicius_santos.todolist.entity.Task;
import com.vinicius_santos.todolist.exception.DescriptionNotProvidedException;
import com.vinicius_santos.todolist.exception.TaskAlreadyCompletedException;
import com.vinicius_santos.todolist.exception.TaskNotFoundException;
import com.vinicius_santos.todolist.exception.TitleNotProvidedException;
import com.vinicius_santos.todolist.repository.TaskRepository;
import com.vinicius_santos.todolist.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;


    @Override
    @Transactional
    public TaskDTO post(TaskDTO taskDto) {
        validar(taskDto);
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .build();
        task.setCreation_date(java.time.LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        return TaskDTO.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .completed(savedTask.isCompleted())
                .build();

    }

    @Override
    @Transactional
    public Task put(TaskDTO taskDto, Long id) {

        validar(taskDto);
        Task taskDB = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskDB.setTitle(taskDto.getTitle());
        taskDB.setDescription(taskDto.getDescription());
        return taskRepository.save(taskDB);
    }

    @Override
    @Transactional
    public Task patch(Long id) {
        Task taskDB = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        if (taskDB.isCompleted()) {
            throw new TaskAlreadyCompletedException("Task already completed");
        }
        taskDB.setCompleted(true);
        taskDB.setCompletion_date(java.time.LocalDateTime.now());
        return taskRepository.save(taskDB);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getAll() {
        return taskRepository.findAll().stream().map(task -> {
            TaskDTO taskDto = new TaskDTO();
            taskDto.setId(task.getId());
            taskDto.setTitle(task.getTitle());
            taskDto.setDescription(task.getDescription());
            taskDto.setCompleted(task.isCompleted());
            return taskDto;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();

    }

    @Override
    public void validar(TaskDTO task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new TitleNotProvidedException("Title is required");
        }
        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new DescriptionNotProvidedException("Description is required");
        }
    }

}
