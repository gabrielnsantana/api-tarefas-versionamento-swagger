package com.example.Tarefa.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TarefaRequest(
    @NotBlank(message = "O título da tarefa é obrigatório.")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
    String titulo, 
    String descricao,
    Boolean concluida
) {}