package com.vinicius_santos.todolist.service.implementation;

import com.vinicius_santos.todolist.dto.TaskDTO;
import com.vinicius_santos.todolist.entity.Task;

import com.vinicius_santos.todolist.exception.DescriptionNotProvidedException;
import com.vinicius_santos.todolist.exception.TaskNotFoundException;
import com.vinicius_santos.todolist.exception.TitleNotProvidedException;
import com.vinicius_santos.todolist.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class TaskServiceImplTest {

    @SpyBean
    public TaskServiceImpl taskService;
    @MockBean
    public  TaskRepository taskRepository;




    @Test
    void shouldCreateTaskSuccessfully() {
        // Arrange

        Task taskToSave = Task.builder()
                .id(1l)
                .title("Task 1")
                .description("Description 1")
                .completed(false)
                .build();

        TaskDTO taskDtoToSave = TaskDTO.builder()
                .id(1l)
                .title("Task 1")
                .description("Description 1")
                .completed(false)
                .build();

        when(taskRepository.save(any(Task.class))).thenReturn(taskToSave);

        // Act
        TaskDTO savedTask = taskService.post(taskDtoToSave);

        // Assert
        assertNotNull(savedTask);
        assertEquals(taskToSave.getTitle(), savedTask.getTitle());
        assertEquals(taskToSave.getDescription(), savedTask.getDescription());
        assertFalse(savedTask.isCompleted());
    }

    @Test
    void shouldThrowTitleNotProvidedExceptionWhenCreateTaskWithInvalidData() {
        TaskDTO taskInvalid = new TaskDTO();
        taskInvalid.setTitle("");
        taskInvalid.setDescription("Description");

        // Act & Assert
        assertThrows(TitleNotProvidedException.class, () -> taskService.post(taskInvalid));
    }

    @Test
    void shouldThrowDescriptionNotProvidedExceptionWhenCreateTaskWithInvalidData() {
        TaskDTO taskInvalid = new TaskDTO();
        taskInvalid.setTitle("Tittle valid");
        taskInvalid.setDescription("");

        // Act & Assert
        assertThrows(DescriptionNotProvidedException.class, () -> taskService.post(taskInvalid));
    }

    @Test
    void shouldUpdateTaskSuccessfully() {
        // Arrange
        Task taskToSave = Task.builder()
                .id(1l)
                .title("Task 1")
                .description("Description 1")
                .completed(false)
                .build();
        when(taskRepository.save(any(Task.class))).thenReturn(taskToSave);


        TaskDTO taskToUpdate =TaskDTO.builder()
                .id(1l)
                .title("Updated Title")
                .description("Updated Description")
                .completed(false)
                .build();

        when(taskRepository.findById(1l)).thenReturn(Optional.of(taskToSave));


        // Act
        Task updatedTask = taskService.put(taskToUpdate, taskToSave.getId());

        // Assert
        assertNotNull(updatedTask);
        assertEquals(taskToUpdate.getTitle(), updatedTask.getTitle());
        assertEquals(taskToUpdate.getDescription(), updatedTask.getDescription());

    }

    @Test
    void shouldThrowExceptionWhenUpdateNonExistentTask() {

        TaskDTO taskToUpdate = TaskDTO.builder()
                .id(1l)
                .title("Updated Title")
                .description("Updated Description")
                .completed(false)
                .build();

        when(taskRepository.findById(1l)).thenReturn(Optional.empty());


        assertThrows(TaskNotFoundException.class, () -> taskService.put(taskToUpdate, 1l));
    }

    @Test
    void shouldMarkTaskAsCompletedSuccessfully() {
        Task taskToComplete = Task.builder()
                .id(1L)
                .title("Task 1")
                .description("Description 1")
                .completed(false)
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskToComplete));
        when(taskRepository.save(any(Task.class))).thenReturn(taskToComplete);

        Task completedTask = taskService.patch(1L);

        assertTrue(completedTask.isCompleted());
    }

    @Test
    void shouldThrowExceptionWhenMarkNonExistentTaskAsCompleted() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.patch(1L));
    }

    @Test
    void shouldDeleteTaskSuccessfully() {
        doNothing().when(taskRepository).deleteById(1L);

        assertDoesNotThrow(() -> taskService.delete(1L));
    }

    @Test
    void shouldThrowExceptionWhenDeleteNonExistentTask() {
        doThrow(EmptyResultDataAccessException.class).when(taskRepository).deleteById(1L);
        assertThrows(TaskNotFoundException.class, () -> taskService.delete(1L));
    }

    @Test
    void shouldRetrieveAllTasksSuccessfully() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder().id(1L).title("Task 1").description("Description 1").completed(false).build());
        tasks.add(Task.builder().id(2L).title("Task 2").description("Description 2").completed(true).build());

        when(taskRepository.findAll()).thenReturn(tasks);

        List<TaskDTO> retrievedTasks = taskService.getAll();

        assertEquals(2, retrievedTasks.size());
    }

    @Test
    void shouldValidateTaskSuccessfully() {
        TaskDTO validTask = TaskDTO.builder().title("Valid Title").description("Valid Description").build();

        assertDoesNotThrow(() -> taskService.validar(validTask));
    }

    @Test
    void shouldThrowExceptionWhenValidateTaskWithInvalidData() {
        TaskDTO invalidTask = TaskDTO.builder().title("").description("").build();

        assertThrows(TitleNotProvidedException.class, () -> taskService.validar(invalidTask));
        invalidTask.setTitle("Valid Title");
        assertThrows(DescriptionNotProvidedException.class, () -> taskService.validar(invalidTask));
    }
}