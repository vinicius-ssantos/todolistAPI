package com.vinicius_santos.todolist.controller;

import com.vinicius_santos.todolist.dto.TaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private TaskDTO taskDTO1;
    private TaskDTO taskDTO2;

    @BeforeEach
    public void setup() {
        // Crie algumas tarefas para teste
        taskDTO1 = TaskDTO.builder().title("Test Task 1").description("Test Description 1").build();
        taskDTO2 = TaskDTO.builder().title("Test Task 2").description("Test Description 2").build();
    }
    @Test
    public void testCreateTask() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test Task");
        taskDTO.setDescription("Test Description");

        ResponseEntity<TaskDTO> response = restTemplate.postForEntity("/tasks", taskDTO, TaskDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Task", response.getBody().getTitle());
        assertEquals("Test Description", response.getBody().getDescription());
    }

    @Test
    public void testGetTasks() {
        // Crie algumas tarefas para teste
        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setTitle("Test Task 1");
        taskDTO1.setDescription("Test Description 1");
        restTemplate.postForEntity("/tasks", taskDTO1, TaskDTO.class);

        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setTitle("Test Task 2");
        taskDTO2.setDescription("Test Description 2");
        restTemplate.postForEntity("/tasks", taskDTO2, TaskDTO.class);

        // Obtenha todas as tarefas
        ResponseEntity<List> response = restTemplate.getForEntity("/tasks", List.class);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() >= 2); // Verifique se pelo menos as duas tarefas criadas estão presentes
    }

    @Test
    public void testUpdateTask() {
        // Crie uma tarefa para teste
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test Task");
        taskDTO.setDescription("Test Description");
        ResponseEntity<TaskDTO> postResponse = restTemplate.postForEntity("/tasks", taskDTO, TaskDTO.class);

        // Atualize a tarefa criada
        taskDTO.setTitle("Updated Task");
        taskDTO.setDescription("Updated Description");
        restTemplate.put("/tasks/" + postResponse.getBody().getId(), taskDTO);

        // Obtenha a tarefa atualizada
        ResponseEntity<TaskDTO> getResponse = restTemplate.getForEntity("/tasks/" + postResponse.getBody().getId(), TaskDTO.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Updated Task", getResponse.getBody().getTitle());
        assertEquals("Updated Description", getResponse.getBody().getDescription());
    }

    @Test
    public void testDeleteTask() {
        // Crie uma tarefa para teste
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test Task");
        taskDTO.setDescription("Test Description");
        ResponseEntity<TaskDTO> postResponse = restTemplate.postForEntity("/tasks", taskDTO, TaskDTO.class);
        restTemplate.delete("/tasks/" + postResponse.getBody().getId(), TaskDTO.class);
        ResponseEntity<TaskDTO> responseTask = null;
        ResponseEntity<String> response = restTemplate.getForEntity("/tasks/" + postResponse.getBody().getId(), String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}