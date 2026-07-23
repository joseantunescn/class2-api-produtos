package br.com.cotiinformatica.api_produtos.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoDeleteResponse(
        int status,
        String mensagem,
        LocalDateTime dataHoraExclusao,
        UUID produtoId
) {
}
