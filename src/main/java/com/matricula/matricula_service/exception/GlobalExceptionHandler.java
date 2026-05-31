package com.matricula.matricula_service.exception;

import com.matricula.matricula_service.dto.ErroResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MatriculaNaoEncontradaException.class)
    public ResponseEntity<ErroResponseDTO> handleNaoEncontrada(
            MatriculaNaoEncontradaException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErroResponseDTO.of(404, "Não Encontrado", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MatriculaDuplicadaException.class)
    public ResponseEntity<ErroResponseDTO> handleDuplicada(
            MatriculaDuplicadaException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErroResponseDTO.of(409, "Conflito", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(OperacaoInvalidaException.class)
    public ResponseEntity<ErroResponseDTO> handleOperacaoInvalida(
            OperacaoInvalidaException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(ErroResponseDTO.of(422, "Operação Inválida", ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> handleValidacao(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> detalhes = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErroResponseDTO.of(400, "Validação", "Campos inválidos", request.getRequestURI(), detalhes));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDTO> handleGenerico(
            Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErroResponseDTO.of(500, "Erro Interno", ex.getMessage(), request.getRequestURI()));
    }
}