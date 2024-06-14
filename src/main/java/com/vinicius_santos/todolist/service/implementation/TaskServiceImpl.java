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


// Esta classe fornece a implementação para a interface TaskService.
// Ela é anotada com @Service para indicar que é uma classe de serviço no contexto do Spring.
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {


    // Injeção do TaskRepository para interagir com o banco de dados.
    @Autowired
    private final TaskRepository taskRepository;

    // Método para criar uma nova tarefa.
    // Ele valida a tarefa antes de salvá-la no banco de dados.
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

    // Método para atualizar uma tarefa existente.
    // Ele valida a tarefa antes de atualizá-la no banco de dados.
    @Override
    @Transactional
    public Task put(TaskDTO taskDto, Long id) {

        validar(taskDto);
        Task taskDB = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskDB.setTitle(taskDto.getTitle());
        taskDB.setDescription(taskDto.getDescription());
        return taskRepository.save(taskDB);
    }

    // Método para marcar uma tarefa como concluída.
    // Ele lança uma exceção se a tarefa já estiver concluída.
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

    // Método para excluir uma tarefa.
    // Ele lança uma exceção se a tarefa não for encontrada.
    @Override
    @Transactional
    public void delete(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
    }

    // Método para obter todas as tarefas.
    // Ele retorna uma lista de objetos TaskDTO.
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

    // Método para obter uma tarefa pelo seu ID.
    // Ele lança uma exceção se a tarefa não for encontrada.
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

    // Método para validar uma tarefa.
    // Ele lança uma exceção se o título ou a descrição não forem fornecidos.
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
