package com.matricula.matricula_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MatriculaRequestDTO(

    @NotNull(message = "O ID do aluno é obrigatório")
    Long alunoId,

    @NotNull(message = "O ID do curso é obrigatório")
    Long cursoId,

    @NotNull(message = "O ID da turma é obrigatório")
    Long turmaId,

    @NotBlank(message = "O nome do aluno é obrigatório")
    @Size(max = 150, message = "Nome do aluno deve ter no máximo 150 caracteres")
    String nomeAluno,

    @NotBlank(message = "O nome do curso é obrigatório")
    @Size(max = 150, message = "Nome do curso deve ter no máximo 150 caracteres")
    String nomeCurso,

    @NotBlank(message = "O nome da turma é obrigatório")
    @Size(max = 100, message = "Nome da turma deve ter no máximo 100 caracteres")
    String nomeTurma,

    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    String observacao
) {}