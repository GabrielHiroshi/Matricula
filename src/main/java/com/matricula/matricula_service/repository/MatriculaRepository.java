package com.matricula.matricula_service.repository;

import com.matricula.matricula_service.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByAlunoId(Long alunoId);

    List<Matricula> findByTurmaId(Long turmaId);

    Optional<Matricula> findByAlunoIdAndTurmaId(Long alunoId, Long turmaId);

    boolean existsByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
}