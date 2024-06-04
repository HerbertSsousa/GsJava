package org.example.db;


import org.example.exception.DataAccessException;
import org.example.Classes.Cadastro;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {

    public void inserir(Cadastro cadastro) throws DataAccessException {
        String sql = "INSERT INTO cadastro (cpf, email, nome_completo, senha, via_cep, login_usuario, produto_id_prod, doacao_id_trans, trab_vol_id_empregado, carrinho_id_carrinho) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cadastro.getCpf());
            stmt.setString(2, cadastro.getEmail());
            stmt.setString(3, cadastro.getNomeCompleto());
            stmt.setString(4, cadastro.getSenha());
            stmt.setString(5, cadastro.getViaCep());
            stmt.setString(6, cadastro.getLoginUsuario());
            stmt.setString(7, cadastro.getProdutoIdProd());
            stmt.setString(8, cadastro.getDoacaoIdTrans());
            stmt.setString(9, cadastro.getTrabVolIdEmpregado());
            stmt.setString(10, cadastro.getCarrinhoIdCarrinho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir cadastro: " + e.getMessage());
        }
    }

    public void deletar(String cpf) throws DataAccessException {
        String sql = "DELETE FROM cadastro WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar cadastro: " + e.getMessage());
        }
    }

    public void alterar(Cadastro cadastro) throws DataAccessException {
        String sql = "UPDATE cadastro SET email = ?, nome_completo = ?, senha = ?, via_cep = ?, login_usuario = ?, produto_id_prod = ?, doacao_id_trans = ?, trab_vol_id_empregado = ?, carrinho_id_carrinho = ? WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cadastro.getEmail());
            stmt.setString(2, cadastro.getNomeCompleto());
            stmt.setString(3, cadastro.getSenha());
            stmt.setString(4, cadastro.getViaCep());
            stmt.setString(5, cadastro.getLoginUsuario());
            stmt.setString(6, cadastro.getProdutoIdProd());
            stmt.setString(7, cadastro.getDoacaoIdTrans());
            stmt.setString(8, cadastro.getTrabVolIdEmpregado());
            stmt.setString(9, cadastro.getCarrinhoIdCarrinho());
            stmt.setString(10, cadastro.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar cadastro: " + e.getMessage());
        }
    }

    public List<Cadastro> selecionarTodos() throws DataAccessException {
        List<Cadastro> cadastros = new ArrayList<>();
        String sql = "SELECT * FROM cadastro";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cadastro cadastro = new Cadastro(
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("nome_completo"),
                        rs.getString("senha"),
                        rs.getString("via_cep"),
                        rs.getString("login_usuario"),
                        rs.getString("produto_id_prod"),
                        rs.getString("doacao_id_trans"),
                        rs.getString("trab_vol_id_empregado"),
                        rs.getString("carrinho_id_carrinho")
                );
                cadastros.add(cadastro);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar todos os cadastros: " + e.getMessage());
        }
        return cadastros;
    }

    public Cadastro selecionarPorCpf(String cpf) throws DataAccessException {
        Cadastro cadastro = null;
        String sql = "SELECT * FROM cadastro WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cadastro = new Cadastro(
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("nome_completo"),
                            rs.getString("senha"),
                            rs.getString("via_cep"),
                            rs.getString("login_usuario"),
                            rs.getString("produto_id_prod"),
                            rs.getString("doacao_id_trans"),
                            rs.getString("trab_vol_id_empregado"),
                            rs.getString("carrinho_id_carrinho")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar cadastro por CPF: " + e.getMessage());
        }
        return cadastro;
    }
}