package com.matricula.matricula_service.controller;

import com.matricula.matricula_service.dto.MatriculaRequestDTO;
import com.matricula.matricula_service.dto.MatriculaResponseDTO;
import com.matricula.matricula_service.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> criar(@Valid @RequestBody MatriculaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaResponseDTO>> buscarPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(service.buscarPorAluno(alunoId));
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<MatriculaResponseDTO>> buscarPorTurma(@PathVariable Long turmaId) {
        return ResponseEntity.ok(service.buscarPorTurma(turmaId));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<MatriculaResponseDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @PatchMapping("/{id}/trancar")
    public ResponseEntity<MatriculaResponseDTO> trancar(@PathVariable Long id) {
        return ResponseEntity.ok(service.trancar(id));
    }
}