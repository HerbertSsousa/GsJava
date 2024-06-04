package org.example.db;


import org.example.Classes.Login;
import org.example.exception.DataAccessException;
import java.sql.*;

public class LoginDAO {

    public void inserir(Login login) throws DataAccessException {
        String sql = "INSERT INTO login (usuario, senha) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login.getUsuario());
            stmt.setString(2, login.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir login: " + e.getMessage());
        }
    }

    public void deletar(String usuario) throws DataAccessException {
        String sql = "DELETE FROM login WHERE usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar login: " + e.getMessage());
        }
    }

    public void alterar(Login login) throws DataAccessException {
        String sql = "UPDATE login SET senha = ? WHERE usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login.getSenha());
            stmt.setString(2, login.getUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar login: " + e.getMessage());
        }
    }

    public Login selecionarPorUsuario(String usuario) throws DataAccessException {
        Login login = null;
        String sql = "SELECT * FROM login WHERE usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    login = new Login();
                    login.setUsuario(rs.getString("usuario"));
                    login.setSenha(rs.getString("senha"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar login por usuário: " + e.getMessage());
        }
        return login;
    }
}