package com.matricula.matricula_service.dto;

import com.matricula.matricula_service.enums.StatusMatricula;
import com.matricula.matricula_service.model.Matricula;

import java.time.LocalDate;

public record MatriculaResponseDTO(
    Long id,
    Long alunoId,
    Long cursoId,
    Long turmaId,
    String nomeAluno,
    String nomeCurso,
    String nomeTurma,
    LocalDate dataMatricula,
    StatusMatricula status,
    String observacao
) {
    public static MatriculaResponseDTO fromEntity(Matricula m) {
        return new MatriculaResponseDTO(
            m.getId(),
            m.getAlunoId(),
            m.getCursoId(),
            m.getTurmaId(),
            m.getNomeAluno(),
            m.getNomeCurso(),
            m.getNomeTurma(),
            m.getDataMatricula(),
            m.getStatus(),
            m.getObservacao()
        );
    }
}