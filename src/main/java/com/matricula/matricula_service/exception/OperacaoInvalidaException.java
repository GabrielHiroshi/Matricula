package com.matricula.matricula_service.exception;

public class OperacaoInvalidaException extends RuntimeException {
    public OperacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}