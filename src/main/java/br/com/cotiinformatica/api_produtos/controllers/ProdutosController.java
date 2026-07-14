package br.com.cotiinformatica.api_produtos.controllers;

import br.com.cotiinformatica.api_produtos.dtos.ProdutoPostRequest;
import br.com.cotiinformatica.api_produtos.dtos.ProdutoPostResponse;
import br.com.cotiinformatica.api_produtos.entities.Produto;
import br.com.cotiinformatica.api_produtos.repositories.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {

    @PostMapping
    public ResponseEntity<ProdutoPostResponse> post(@RequestBody ProdutoPostRequest request) {
        try {
            var produto = new Produto();
            produto.setId(UUID.randomUUID());
            produto.setNome(request.nome());
            produto.setPreco(request.preco());
            produto.setQuantidade(request.quantidade());
            produto.setDataHoraCadastro(java.time.LocalDateTime.now());
            produto.setAtivo(true); //Definindo o produto como ativo (true)


            var produtoRepository = new ProdutoRepository();
            produtoRepository.create(produto);

            var response = new ProdutoPostResponse  (
                    201,
                    "Produto cadastrado com sucesso",
                    LocalDateTime.now(),
                    produto.getId()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }
        catch (Exception e) {
            var response = new ProdutoPostResponse  (
                    500,
                    "Erro ao cadastrar produto" + e.getMessage(),
                    LocalDateTime.now(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PutMapping
    public ResponseEntity<?> put() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }

}



