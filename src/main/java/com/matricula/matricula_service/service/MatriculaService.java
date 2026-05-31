package com.matricula.matricula_service.service;

import com.matricula.matricula_service.dto.MatriculaRequestDTO;
import com.matricula.matricula_service.dto.MatriculaResponseDTO;
import com.matricula.matricula_service.enums.StatusMatricula;
import com.matricula.matricula_service.exception.MatriculaDuplicadaException;
import com.matricula.matricula_service.exception.MatriculaNaoEncontradaException;
import com.matricula.matricula_service.exception.OperacaoInvalidaException;
import com.matricula.matricula_service.model.Matricula;
import com.matricula.matricula_service.repository.MatriculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatriculaService {

    private final MatriculaRepository repository;

    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MatriculaResponseDTO criar(MatriculaRequestDTO dto) {
        if (repository.existsByAlunoIdAndTurmaId(dto.alunoId(), dto.turmaId())) {
            throw new MatriculaDuplicadaException(dto.alunoId(), dto.turmaId());
        }

        Matricula matricula = Matricula.builder()
            .alunoId(dto.alunoId())
            .cursoId(dto.cursoId())
            .turmaId(dto.turmaId())
            .nomeAluno(dto.nomeAluno())
            .nomeCurso(dto.nomeCurso())
            .nomeTurma(dto.nomeTurma())
            .dataMatricula(LocalDate.now())
            .status(StatusMatricula.ATIVA)
            .observacao(dto.observacao())
            .build();

        return MatriculaResponseDTO.fromEntity(repository.save(matricula));
    }

    @Transactional(readOnly = true)
    public List<MatriculaResponseDTO> listarTodas() {
        return repository.findAll()
            .stream()
            .map(MatriculaResponseDTO::fromEntity)
            .toList();
    }

    @Transactional(readOnly = true)
    public MatriculaResponseDTO buscarPorId(Long id) {
        return MatriculaResponseDTO.fromEntity(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public List<MatriculaResponseDTO> buscarPorAluno(Long alunoId) {
        return repository.findByAlunoId(alunoId)
            .stream()
            .map(MatriculaResponseDTO::fromEntity)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<MatriculaResponseDTO> buscarPorTurma(Long turmaId) {
        return repository.findByTurmaId(turmaId)
            .stream()
            .map(MatriculaResponseDTO::fromEntity)
            .toList();
    }

    @Transactional
    public MatriculaResponseDTO cancelar(Long id) {
        Matricula matricula = buscarEntidade(id);
        if (matricula.getStatus() == StatusMatricula.CANCELADA) {
            throw new OperacaoInvalidaException("A matrícula já está cancelada.");
        }
        if (matricula.getStatus() == StatusMatricula.CONCLUIDA) {
            throw new OperacaoInvalidaException("Não é possível cancelar uma matrícula concluída.");
        }
        matricula.setStatus(StatusMatricula.CANCELADA);
        return MatriculaResponseDTO.fromEntity(repository.save(matricula));
    }

    @Transactional
    public MatriculaResponseDTO trancar(Long id) {
        Matricula matricula = buscarEntidade(id);
        if (matricula.getStatus() != StatusMatricula.ATIVA) {
            throw new OperacaoInvalidaException(
                "Apenas matrículas ATIVAS podem ser trancadas. Status atual: " + matricula.getStatus()
            );
        }
        matricula.setStatus(StatusMatricula.TRANCADA);
        return MatriculaResponseDTO.fromEntity(repository.save(matricula));
    }

    private Matricula buscarEntidade(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new MatriculaNaoEncontradaException(id));
    }
}