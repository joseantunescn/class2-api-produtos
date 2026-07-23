package br.com.cotiinformatica.api_produtos.controllers;

import br.com.cotiinformatica.api_produtos.dtos.*;
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
    public ResponseEntity<?> put(@RequestBody ProdutoPutRequest request) {


        try {

            var produto = new Produto();
            produto.setId(request.id());
            produto.setNome(request.nome());
            produto.setPreco(request.preco());
            produto.setQuantidade(request.quantidade());

            var produtoRepository = new ProdutoRepository();
            if(produtoRepository.update(produto)){
                var response = new ProdutoPutResponse(200, "Produto Atualizado com sucesso!", LocalDateTime.now(), produto.getId());
                return ResponseEntity.status(200).body(response);

            }
            else{
                return ResponseEntity.status(404).body("Produto não encontrado!");
            }

        }
        catch(Exception e) {
            var response = new ProdutoPutResponse(500, "Falha ao atualizar produto!" + e.getMessage(), LocalDateTime.now(), null);
            return ResponseEntity.status(500).body(response);

        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try{
            var produtoRepository = new ProdutoRepository();
            if(produtoRepository.delete(id)) {
                var response = new ProdutoDeleteResponse(200, "Produto excluído com sucesso!", LocalDateTime.now(), id);
                return  ResponseEntity.status(200).body(response);

            }
            else{
                return ResponseEntity.status(404).body("Produto não encontrado para exclusão.");
            }

        }
        catch (Exception e) {
            var response = new ProdutoDeleteResponse(500, "Falha ao deleter" + e.getMessage(), LocalDateTime.now(), null);
            return ResponseEntity.status(500).body(response);
        }

    }

    @GetMapping
    public ResponseEntity<?> get() {
        try{
            var produtoRepository = new ProdutoRepository();
            var produtos = produtoRepository.read();
            return ResponseEntity.status(200).body(produtos);

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao consultar ..." + e.getMessage());

        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try{
            var produtoRepository = new ProdutoRepository();
            var produto = produtoRepository.readById(id);
            return ResponseEntity.status(200).body(produto);

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao consultar produto ..." + e.getMessage());
        }
    }



}



