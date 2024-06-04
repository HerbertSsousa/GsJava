package org.example.db;


import org.example.exception.DataAccessException;
import org.example.Classes.Carrinho;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {

    public void inserir(Carrinho carrinho) throws DataAccessException {
        String sql = "INSERT INTO carrinho (id_carrinho, adc_prod) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carrinho.getIdCarrinho());
            stmt.setString(2, carrinho.getAdcProd());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir carrinho: " + e.getMessage());
        }
    }

    public void deletar(String idCarrinho) throws DataAccessException {
        String sql = "DELETE FROM carrinho WHERE id_carrinho = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idCarrinho);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar carrinho: " + e.getMessage());
        }
    }

    public void alterar(Carrinho carrinho) throws DataAccessException {
        String sql = "UPDATE carrinho SET adc_prod = ? WHERE id_carrinho = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carrinho.getAdcProd());
            stmt.setString(2, carrinho.getIdCarrinho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar carrinho: " + e.getMessage());
        }
    }

    public List<Carrinho> selecionarTodos() throws DataAccessException {
        List<Carrinho> carrinhos = new ArrayList<>();
        String sql = "SELECT * FROM carrinho";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Carrinho carrinho = new Carrinho();
                carrinho.setIdCarrinho(rs.getString("id_carrinho"));
                carrinho.setAdcProd(rs.getString("adc_prod"));
                carrinhos.add(carrinho);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar todos os carrinhos: " + e.getMessage());
        }
        return carrinhos;
    }

    public Carrinho selecionarPorId(String idCarrinho) throws DataAccessException {
        Carrinho carrinho = null;
        String sql = "SELECT * FROM carrinho WHERE id_carrinho = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idCarrinho);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    carrinho = new Carrinho();
                    carrinho.setIdCarrinho(rs.getString("id_carrinho"));
                    carrinho.setAdcProd(rs.getString("adc_prod"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar carrinho por ID: " + e.getMessage());
        }
        return carrinho;
    }
}