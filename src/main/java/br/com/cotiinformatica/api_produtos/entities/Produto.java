package br.com.cotiinformatica.api_produtos.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Produto {

    private UUID id;
    private String nome;
    private Double preco;
    private Integer quantidade;
    private LocalDateTime dataHoraCadastro;
    private Boolean ativo;




}
