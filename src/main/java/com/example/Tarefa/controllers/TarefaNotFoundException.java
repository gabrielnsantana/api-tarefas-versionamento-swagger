package com.example.Tarefa.controllers;

public class TarefaNotFoundException extends RuntimeException {
    public TarefaNotFoundException(Long id) {
        super("Tarefa com o ID " + id + " não foi encontrada no sistema.");
    }
}
