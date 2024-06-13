package com.vinicius_santos.todolist.exception;

public class DescriptionNotProvidedException extends RuntimeException {
    public DescriptionNotProvidedException(String message) {
        super(message);
    }
}