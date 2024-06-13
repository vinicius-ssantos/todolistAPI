package com.vinicius_santos.todolist.exception;

public class TitleNotProvidedException extends RuntimeException {
    public TitleNotProvidedException(String message) {
        super(message);
    }
}