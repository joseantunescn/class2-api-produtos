package br.com.cotiinformatica.api_produtos.repositories;

import br.com.cotiinformatica.api_produtos.Factories.ConnectionFactory;
import br.com.cotiinformatica.api_produtos.entities.Produto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class ProdutoRepository {

    public void create(Produto produto) throws Exception {
        // code to create a product in the database
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("insert into produtos (id, nome, preco, quantidade, data_hora_cadastro, ativo) values (?,?,?,?,?,?)");
            statement.setObject(1, produto.getId());
            statement.setString(2, produto.getNome());
            statement.setDouble(3, produto.getPreco());
            statement.setInt(4, produto.getQuantidade());
            statement.setTimestamp(5, Timestamp.valueOf(produto.getDataHoraCadastro()));
            statement.setBoolean(6, produto.getAtivo());
            statement.execute();
        }
    }

    public boolean update(Produto produto) throws Exception {
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("update produtos set nome = ?, preco = ?, quantidade = ?, ativo = ? where id = ?");
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setObject(4, produto.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(UUID id) throws Exception {
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("update produtos set ativo = false where id = ?");
            statement.setObject(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Produto> read() throws Exception {
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("select * from produtos where ativo = true");
            var result = statement.executeQuery();
            var lista = new java.util.ArrayList<Produto>();
            while (result.next()) {
                var produto = new Produto();
                produto.setId((UUID) result.getObject("id"));
                produto.setNome(result.getString("nome"));
                produto.setPreco(result.getDouble("preco"));
                produto.setQuantidade(result.getInt("quantidade"));
                produto.setDataHoraCadastro(result.getTimestamp("data_hora_cadastro").toLocalDateTime());
                produto.setAtivo(result.getBoolean("ativo"));
                lista.add(produto);
            }
            return lista;
        }
    }


}
