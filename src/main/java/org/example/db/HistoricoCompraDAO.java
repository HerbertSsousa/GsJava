package org.example.db;

import org.example.Classes.HistoricoCompra;
import org.example.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoCompraDAO {

    public void inserir(HistoricoCompra historicoCompra) throws DataAccessException {
        String sql = "INSERT INTO historico_compra (id_historico, compras) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, historicoCompra.getIdHistorico());
            stmt.setString(2, historicoCompra.getCompras());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir histórico de compra: " + e.getMessage());
        }
    }

    public void deletar(String idHistorico) throws DataAccessException {
        String sql = "DELETE FROM historico_compra WHERE id_historico = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idHistorico);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar histórico de compra: " + e.getMessage());
        }
    }

    public void alterar(HistoricoCompra historicoCompra) throws DataAccessException {
        String sql = "UPDATE historico_compra SET compras = ? WHERE id_historico = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, historicoCompra.getCompras());
            stmt.setString(2, historicoCompra.getIdHistorico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar histórico de compra: " + e.getMessage());
        }
    }

    public List<HistoricoCompra> selecionarTodos() throws DataAccessException {
        List<HistoricoCompra> historicoCompras = new ArrayList<>();
        String sql = "SELECT * FROM historico_compra";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                HistoricoCompra historicoCompra = new HistoricoCompra();
                historicoCompra.setIdHistorico(rs.getString("id_historico"));
                historicoCompra.setCompras(rs.getString("compras"));
                historicoCompras.add(historicoCompra);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar todos os históricos de compra: " + e.getMessage());
        }
        return historicoCompras;
    }

    public HistoricoCompra selecionarPorId(String idHistorico) throws DataAccessException {
        HistoricoCompra historicoCompra = null;
        String sql = "SELECT * FROM historico_compra WHERE id_historico = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idHistorico);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    historicoCompra = new HistoricoCompra();
                    historicoCompra.setIdHistorico(rs.getString("id_historico"));
                    historicoCompra.setCompras(rs.getString("compras"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar histórico de compra por ID: " + e.getMessage());
        }
        return historicoCompra;
    }
}