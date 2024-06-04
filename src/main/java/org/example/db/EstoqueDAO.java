package org.example.db;


import org.example.Classes.Estoque;


import org.example.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    public void inserir(Estoque estoque) throws DataAccessException {
        String sql = "INSERT INTO estoque (id_prod, quantidades, carrinho_id_carrinho) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estoque.getIdProd());
            stmt.setString(2, estoque.getQuantidades());
            stmt.setString(3, estoque.getCarrinhoIdCarrinho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir estoque: " + e.getMessage());
        }
    }

    public void deletar(String idProd) throws DataAccessException {
        String sql = "DELETE FROM estoque WHERE id_prod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idProd);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar estoque: " + e.getMessage());
        }
    }

    public void alterar(Estoque estoque) throws DataAccessException {
        String sql = "UPDATE estoque SET quantidades = ?, carrinho_id_carrinho = ? WHERE id_prod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estoque.getQuantidades());
            stmt.setString(2, estoque.getCarrinhoIdCarrinho());
            stmt.setString(3, estoque.getIdProd());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar estoque: " + e.getMessage());
        }
    }

    public List<Estoque> selecionarTodos() throws DataAccessException {
        List<Estoque> estoques = new ArrayList<>();
        String sql = "SELECT * FROM estoque";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setIdProd(rs.getString("id_prod"));
                estoque.setQuantidades(rs.getString("quantidades"));
                estoque.setCarrinhoIdCarrinho(rs.getString("carrinho_id_carrinho"));
                estoques.add(estoque);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar todos os estoques: " + e.getMessage());
        }
        return estoques;
    }

    public Estoque selecionarPorId(String idProd) throws DataAccessException {
        Estoque estoque = null;
        String sql = "SELECT * FROM estoque WHERE id_prod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idProd);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estoque = new Estoque();
                    estoque.setIdProd(rs.getString("id_prod"));
                    estoque.setQuantidades(rs.getString("quantidades"));
                    estoque.setCarrinhoIdCarrinho(rs.getString("carrinho_id_carrinho"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar estoque por ID: " + e.getMessage());
        }
        return estoque;
    }
}