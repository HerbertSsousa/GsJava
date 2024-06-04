package org.example.db;

import org.example.Classes.Pagamento;

import org.example.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void inserir(Pagamento pagamento) throws DataAccessException {
        String sql = "INSERT INTO pagamento (form_pag, cpf, doacao_id_trans, historico_compra_id_historico) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getFormPag());
            stmt.setString(2, pagamento.getCpf());
            stmt.setString(3, pagamento.getDoacaoIdTrans());
            stmt.setString(4, pagamento.getHistoricoCompraIdHistorico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir pagamento: " + e.getMessage());
        }
    }

    public void deletar(String cpf) throws DataAccessException {
        String sql = "DELETE FROM pagamento WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar pagamento: " + e.getMessage());
        }
    }

    public void alterar(Pagamento pagamento) throws DataAccessException {
        String sql = "UPDATE pagamento SET form_pag = ?, doacao_id_trans = ?, historico_compra_id_historico = ? WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getFormPag());
            stmt.setString(2, pagamento.getDoacaoIdTrans());
            stmt.setString(3, pagamento.getHistoricoCompraIdHistorico());
            stmt.setString(4, pagamento.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar pagamento: " + e.getMessage());
        }
    }

    public Pagamento selecionarPorCpf(String cpf) throws DataAccessException {
        Pagamento pagamento = null;
        String sql = "SELECT * FROM pagamento WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pagamento = new Pagamento();
                    pagamento.setFormPag(rs.getString("form_pag"));
                    pagamento.setCpf(rs.getString("cpf"));
                    pagamento.setDoacaoIdTrans(rs.getString("doacao_id_trans"));
                    pagamento.setHistoricoCompraIdHistorico(rs.getString("historico_compra_id_historico"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar pagamento por CPF: " + e.getMessage());
        }
        return pagamento;
    }

    public List<Pagamento> selecionarTodos() throws DataAccessException {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM pagamento";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setFormPag(rs.getString("form_pag"));
                pagamento.setCpf(rs.getString("cpf"));
                pagamento.setDoacaoIdTrans(rs.getString("doacao_id_trans"));
                pagamento.setHistoricoCompraIdHistorico(rs.getString("historico_compra_id_historico"));
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar todos os pagamentos: " + e.getMessage());
        }
        return pagamentos;
    }
}