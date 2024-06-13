package com.vinicius_santos.todolist.service;

import com.vinicius_santos.todolist.dto.TaskDTO;
import com.vinicius_santos.todolist.entity.Task;

import java.util.List;

public interface TaskService {

    TaskDTO post(TaskDTO task)  ;

    Task put(TaskDTO task, Long id);
    Task patch( Long id);

    void delete(Long id);

    List<TaskDTO> getAll();
    TaskDTO getById(Long id);


    void validar(TaskDTO task);


}
