package com.vinicius_santos.todolist.repository;

import com.vinicius_santos.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    @Override
    void deleteById(Long id) throws EntityNotFoundException;



}