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

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {



    @Autowired
    public   TaskService taskService;

    @Operation(summary = "Criar uma nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna a tarefa criada"),
            @ApiResponse(responseCode = "400", description = "Caso haja um erro ao criar a tarefa")
    })
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            TaskDTO taskCreated = taskService.post(taskDTO);
            return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Atualizar uma tarefa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a tarefa atualizada"),
            @ApiResponse(responseCode = "400", description = "Caso haja um erro ao atualizar a tarefa")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.put(taskDTO, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Deletar uma tarefa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna OK se a tarefa foi deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Caso haja um erro ao deletar a tarefa")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Obter todas as tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de tarefas"),
            @ApiResponse(responseCode = "400", description = "Caso haja um erro interno no servidor"),
    })
    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        try {
            return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Obter uma tarefa específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a tarefa solicitada"),
            @ApiResponse(responseCode = "400", description = "Caso haja um erro ao buscar a tarefa")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Marcar uma tarefa como completa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a tarefa marcada como completa"),
            @ApiResponse(responseCode = "400", description = "Caso haja um erro ao marcar a tarefa como completa")
    })
    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> taskComplete( @PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.patch(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}