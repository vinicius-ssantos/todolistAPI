package com.vinicius_santos.todolist.controller;

import com.vinicius_santos.todolist.dto.TaskDTO;
import com.vinicius_santos.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Esta classe é o controlador para as operações relacionadas às tarefas.
// Ela é anotada com @RestController, indicando que é um controlador REST no Spring MVC.
// A anotação @RequestMapping("/tasks") indica que este controlador lida com todas as requisições que começam com "/tasks".
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    // Injeção do serviço de tarefas para realizar operações relacionadas às tarefas.
    @Autowired
    public   TaskService taskService;

    // Método para criar uma nova tarefa.
    // Ele recebe um objeto TaskDTO no corpo da requisição e retorna a tarefa criada.
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            TaskDTO taskCreated = taskService.post(taskDTO);
            return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para atualizar uma tarefa existente.
    // Ele recebe um objeto TaskDTO no corpo da requisição e o ID da tarefa a ser atualizada.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.put(taskDTO, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para excluir uma tarefa.
    // Ele recebe o ID da tarefa a ser excluída.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obter todas as tarefas.
    // Ele retorna uma lista de todas as tarefas.
    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        try {
            return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Método para obter uma tarefa específica pelo ID.
    // Ele recebe o ID da tarefa a ser obtida.
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Método para marcar uma tarefa como completa.
    // Ele recebe o ID da tarefa a ser marcada como completa.
    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> taskComplete( @PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.patch(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}