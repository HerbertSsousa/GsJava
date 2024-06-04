package org.example.db;

import org.example.Classes.Doacao;
import org.example.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {

    public void inserir(Doacao doacao) throws DataAccessException {
        String sql = "INSERT INTO doacao (id_trans, valor, destinatario) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doacao.getIdTrans());
            stmt.setString(2, doacao.getValor());
            stmt.setString(3, doacao.getDestinatario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir doação: " + e.getMessage());
        }
    }

    public void deletar(String idTrans) throws DataAccessException {
        String sql = "DELETE FROM doacao WHERE id_trans = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idTrans);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar doação: " + e.getMessage());
        }
    }

    public void alterar(Doacao doacao) throws DataAccessException {
        String sql = "UPDATE doacao SET valor = ?, destinatario = ? WHERE id_trans = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doacao.getValor());
            stmt.setString(2, doacao.getDestinatario());
            stmt.setString(3, doacao.getIdTrans());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar doação: " + e.getMessage());
        }
    }

    public List<Doacao> selecionarTodos() throws DataAccessException {
        List<Doacao> doacoes = new ArrayList<>();
        String sql = "SELECT * FROM doacao";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Doacao doacao = new Doacao();
                doacao.setIdTrans(rs.getString("id_trans"));
                doacao.setValor(rs.getString("valor"));
                doacao.setDestinatario(rs.getString("destinatario"));
                doacoes.add(doacao);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar todas as doações: " + e.getMessage());
        }
        return doacoes;
    }

    public Doacao selecionarPorId(String idTrans) throws DataAccessException {
        Doacao doacao = null;
        String sql = "SELECT * FROM doacao WHERE id_trans = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idTrans);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    doacao = new Doacao();
                    doacao.setIdTrans(rs.getString("id_trans"));
                    doacao.setValor(rs.getString("valor"));
                    doacao.setDestinatario(rs.getString("destinatario"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar doação por ID: " + e.getMessage());
        }
        return doacao;
    }
}