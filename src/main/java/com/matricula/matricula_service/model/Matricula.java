package com.matricula.matricula_service.model;

import com.matricula.matricula_service.enums.StatusMatricula;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
    name = "matriculas",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_aluno_turma",
        columnNames = {"aluno_id", "turma_id"}
    )
)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aluno_id", nullable = false)
    private Long alunoId;

    @Column(name = "curso_id", nullable = false)
    private Long cursoId;

    @Column(name = "turma_id", nullable = false)
    private Long turmaId;

    @Column(name = "nome_aluno", nullable = false, length = 150)
    private String nomeAluno;

    @Column(name = "nome_curso", nullable = false, length = 150)
    private String nomeCurso;

    @Column(name = "nome_turma", nullable = false, length = 100)
    private String nomeTurma;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusMatricula status;

    @Column(length = 500)
    private String observacao;

    // ---- Construtores ----

    public Matricula() {}

    public Matricula(Long id, Long alunoId, Long cursoId, Long turmaId,
                     String nomeAluno, String nomeCurso, String nomeTurma,
                     LocalDate dataMatricula, StatusMatricula status, String observacao) {
        this.id = id;
        this.alunoId = alunoId;
        this.cursoId = cursoId;
        this.turmaId = turmaId;
        this.nomeAluno = nomeAluno;
        this.nomeCurso = nomeCurso;
        this.nomeTurma = nomeTurma;
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.observacao = observacao;
    }

    // ---- Getters ----

    public Long getId() { return id; }
    public Long getAlunoId() { return alunoId; }
    public Long getCursoId() { return cursoId; }
    public Long getTurmaId() { return turmaId; }
    public String getNomeAluno() { return nomeAluno; }
    public String getNomeCurso() { return nomeCurso; }
    public String getNomeTurma() { return nomeTurma; }
    public LocalDate getDataMatricula() { return dataMatricula; }
    public StatusMatricula getStatus() { return status; }
    public String getObservacao() { return observacao; }

    // ---- Setters ----

    public void setId(Long id) { this.id = id; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    public void setTurmaId(Long turmaId) { this.turmaId = turmaId; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
    public void setNomeCurso(String nomeCurso) { this.nomeCurso = nomeCurso; }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    public void setStatus(StatusMatricula status) { this.status = status; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    // ---- Builder manual ----

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long alunoId;
        private Long cursoId;
        private Long turmaId;
        private String nomeAluno;
        private String nomeCurso;
        private String nomeTurma;
        private LocalDate dataMatricula;
        private StatusMatricula status;
        private String observacao;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder alunoId(Long alunoId) { this.alunoId = alunoId; return this; }
        public Builder cursoId(Long cursoId) { this.cursoId = cursoId; return this; }
        public Builder turmaId(Long turmaId) { this.turmaId = turmaId; return this; }
        public Builder nomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; return this; }
        public Builder nomeCurso(String nomeCurso) { this.nomeCurso = nomeCurso; return this; }
        public Builder nomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; return this; }
        public Builder dataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; return this; }
        public Builder status(StatusMatricula status) { this.status = status; return this; }
        public Builder observacao(String observacao) { this.observacao = observacao; return this; }

        public Matricula build() {
            return new Matricula(id, alunoId, cursoId, turmaId, nomeAluno,
                nomeCurso, nomeTurma, dataMatricula, status, observacao);
        }
    }

    @PrePersist
    public void prePersist() {
        if (this.dataMatricula == null) this.dataMatricula = LocalDate.now();
        if (this.status == null) this.status = StatusMatricula.ATIVA;
    }
}