package br.com.cotiinformatica.api_produtos.dtos;

public record ProdutoPostRequest(

        String nome,
        Double preco,
        Integer quantidade

) {
}
