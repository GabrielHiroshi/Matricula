package com.matricula.matricula_service.exception;

public class MatriculaDuplicadaException extends RuntimeException {
    public MatriculaDuplicadaException(Long alunoId, Long turmaId) {
        super("O aluno com ID " + alunoId + " já possui matrícula na turma com ID " + turmaId);
    }
}