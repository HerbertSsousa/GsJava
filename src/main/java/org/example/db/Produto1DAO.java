package org.example.db;


import org.example.Classes.Produto1;

import org.example.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Produto1DAO {

    public void inserir(Produto1 produto) throws DataAccessException {
        String sql = "INSERT INTO produto (id_prod, preco, tipo_prod, nome_prod, pagamento_form_pag, estoque_id_prod, carrinho_id_carrinho) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getIdProd());
            stmt.setString(2, produto.getPreco());
            stmt.setString(3, produto.getTipoProd());
            stmt.setString(4, produto.getNomeProd());
            stmt.setString(5, produto.getPagamentoFormPag());
            stmt.setString(6, produto.getEstoqueIdProd());
            stmt.setString(7, produto.getCarrinhoIdCarrinho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public void deletar(String idProd) throws DataAccessException {
        String sql = "DELETE FROM produto WHERE id_prod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idProd);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar produto: " + e.getMessage());
        }
    }

    public void alterar(Produto1 produto) throws DataAccessException {
        String sql = "UPDATE produto SET preco = ?, tipo_prod = ?, nome_prod = ?, pagamento_form_pag = ?, estoque_id_prod = ?, carrinho_id_carrinho = ? WHERE id_prod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getPreco());
            stmt.setString(2, produto.getTipoProd());
            stmt.setString(3, produto.getNomeProd());
            stmt.setString(4, produto.getPagamentoFormPag());
            stmt.setString(5, produto.getEstoqueIdProd());
            stmt.setString(6, produto.getCarrinhoIdCarrinho());
            stmt.setString(7, produto.getIdProd());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar produto: " + e.getMessage());
        }
    }

    public Produto1 selecionarPorId(String idProd) throws DataAccessException {
        Produto1 produto = null;
        String sql = "SELECT * FROM produto WHERE id_prod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idProd);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto1();
                    produto.setIdProd(rs.getString("id_prod"));
                    produto.setPreco(rs.getString("preco"));
                    produto.setTipoProd(rs.getString("tipo_prod"));
                    produto.setNomeProd(rs.getString("nome_prod"));
                    produto.setPagamentoFormPag(rs.getString("pagamento_form_pag"));
                    produto.setEstoqueIdProd(rs.getString("estoque_id_prod"));
                    produto.setCarrinhoIdCarrinho(rs.getString("carrinho_id_carrinho"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar produto por ID: " + e.getMessage());
        }
        return produto;
    }

    public List<Produto1> selecionarTodos() throws DataAccessException {
        List<Produto1> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto1 produto = new Produto1();
                produto.setIdProd(rs.getString("id_prod"));
                produto.setPreco(rs.getString("preco"));
                produto.setTipoProd(rs.getString("tipo_prod"));
                produto.setNomeProd(rs.getString("nome_prod"));
                produto.setPagamentoFormPag(rs.getString("pagamento_form_pag"));
                produto.setEstoqueIdProd(rs.getString("estoque_id_prod"));
                produto.setCarrinhoIdCarrinho(rs.getString("carrinho_id_carrinho"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar todos os produtos: " + e.getMessage());
        }
        return produtos;
    }
}