package com.example.Tarefa.controllers;

import java.time.LocalDateTime;

public record ErrorMessage(
    int status,
    String error,
    String message,
    LocalDateTime timestamp
) {}