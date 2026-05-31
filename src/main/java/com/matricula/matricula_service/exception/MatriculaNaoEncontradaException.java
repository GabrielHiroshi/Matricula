package com.matricula.matricula_service.exception;

public class MatriculaNaoEncontradaException extends RuntimeException {
    public MatriculaNaoEncontradaException(Long id) {
        super("Matrícula não encontrada com ID: " + id);
    }
}