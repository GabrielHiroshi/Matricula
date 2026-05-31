package com.matricula.matricula_service.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResponseDTO(
    LocalDateTime timestamp,
    int status,
    String erro,
    String mensagem,
    String caminho,
    List<String> detalhes
) {
    public static ErroResponseDTO of(int status, String erro, String mensagem, String caminho) {
        return new ErroResponseDTO(LocalDateTime.now(), status, erro, mensagem, caminho, null);
    }

    public static ErroResponseDTO of(int status, String erro, String mensagem, String caminho, List<String> detalhes) {
        return new ErroResponseDTO(LocalDateTime.now(), status, erro, mensagem, caminho, detalhes);
    }
}