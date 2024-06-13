package com.vinicius_santos.todolist.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "task" , schema = "todolist")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private LocalDateTime creation_date;
    @Column(name = "completion_date")
    private LocalDateTime completion_date;
    @Column(name = "completed")
    private boolean completed=false;


}