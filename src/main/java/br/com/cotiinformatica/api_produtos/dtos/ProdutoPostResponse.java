package br.com.cotiinformatica.api_produtos.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoPostResponse(
        Integer status,
        String mensagem,
        LocalDateTime dataHora,
        UUID produtoId
) {
}
