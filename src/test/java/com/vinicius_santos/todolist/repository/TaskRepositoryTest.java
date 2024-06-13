package com.vinicius_santos.todolist.repository;

import com.vinicius_santos.todolist.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class) // para rodar com o spring runner (não precisa do @SpringBootTest)
@DataJpaTest // para testes de repository (não carrega o contexto da aplicação)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// para não substituir o banco de dados de produção pelo H2 em memória
@ActiveProfiles("test")
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TestEntityManager entityManager;

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }


    @Test
    public void shouldSaveTaskWithAllFieldsCorrectly() {
        Task task = createTask();
        Task savedTask = taskRepository.save(task);
        assertNotNull(savedTask.getId());
        assertEquals("Task 1", savedTask.getTitle());
        assertEquals("Description 1", savedTask.getDescription());
        assertFalse(savedTask.isCompleted());
    }


    @Test
    public void shouldUpdateTaskSuccessfully() {
        Task task = createAndPersistTask();
        task.setTitle("Task 2");
        task.setDescription("Description 2");
        task.setCompleted(true);
        taskRepository.save(task);
        Task updatedTask = entityManager.find(Task.class, task.getId());
        assertEquals("Task 2", updatedTask.getTitle());
        assertEquals("Description 2", updatedTask.getDescription());
        assertTrue(updatedTask.isCompleted());
    }

    @Test
    public void shouldDeleteTaskSuccessfully() {
        Task task = createAndPersistTask();
        taskRepository.deleteById(task.getId());
        Task deletedTask = entityManager.find(Task.class, task.getId());
        assertNull(deletedTask);
    }


    @Test
    public void shouldRetrieveAllTasksSuccessfully() {
        List<Task> tasks = List.of(createAndPersistTask(), createAndPersistTask(), createAndPersistTask());
        List<Task> tasksDB = taskRepository.findAll();
        assertEquals(tasks.size(), tasksDB.size());
        assertTrue(tasks.containsAll(tasksDB));
    }

    @Test
    public void shouldRetrieveTaskByIdSuccessfully() {
        Task task = createAndPersistTask();
        Optional<Task> taskDB = taskRepository.findById(task.getId());
        assertEquals(task, taskDB.get());

    }

    public  Task createTask() {
        return Task.builder()
                .title("Task 1")
                .description("Description 1")
                .completed(false)
                .build();
    }
    public Task createAndPersistTask() {
        Task task = createTask();
        return entityManager.persist(task);
    }
}