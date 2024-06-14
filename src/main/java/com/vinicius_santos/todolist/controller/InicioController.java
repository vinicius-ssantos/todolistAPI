package com.vinicius_santos.todolist.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class InicioController {
    @GetMapping
    public String inicio(){
        return "Bem vindo a API TODO LIST";
    }

}
