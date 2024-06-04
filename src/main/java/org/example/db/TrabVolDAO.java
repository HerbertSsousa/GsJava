package org.example.db;



import org.example.Classes.TrabVol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabVolDAO {

    public void inserir(TrabVol trabVol) throws SQLException {
        String sql = "INSERT INTO trab_vol (id_empregado, area_int, idade, cep) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trabVol.getIdEmpregado());
            stmt.setString(2, trabVol.getAreaInt());
            stmt.setString(3, trabVol.getIdade());
            stmt.setString(4, trabVol.getCep());
            stmt.executeUpdate();
        }
    }

    public void deletar(String idEmpregado) throws SQLException {
        String sql = "DELETE FROM trab_vol WHERE id_empregado = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idEmpregado);
            stmt.executeUpdate();
        }
    }

    public void alterar(TrabVol trabVol) throws SQLException {
        String sql = "UPDATE trab_vol SET area_int = ?, idade = ?, cep = ? WHERE id_empregado = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trabVol.getAreaInt());
            stmt.setString(2, trabVol.getIdade());
            stmt.setString(3, trabVol.getCep());
            stmt.setString(4, trabVol.getIdEmpregado());
            stmt.executeUpdate();
        }
    }

    public TrabVol selecionarPorId(String idEmpregado) throws SQLException {
        TrabVol trabVol = null;
        String sql = "SELECT * FROM trab_vol WHERE id_empregado = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idEmpregado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    trabVol = new TrabVol();
                    trabVol.setIdEmpregado(rs.getString("id_empregado"));
                    trabVol.setAreaInt(rs.getString("area_int"));
                    trabVol.setIdade(rs.getString("idade"));
                    trabVol.setCep(rs.getString("cep"));
                }
            }
        }
        return trabVol;
    }

    public List<TrabVol> selecionarTodos() throws SQLException {
        List<TrabVol> trabVolList = new ArrayList<>();
        String sql = "SELECT * FROM trab_vol";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TrabVol trabVol = new TrabVol();
                trabVol.setIdEmpregado(rs.getString("id_empregado"));
                trabVol.setAreaInt(rs.getString("area_int"));
                trabVol.setIdade(rs.getString("idade"));
                trabVol.setCep(rs.getString("cep"));
                trabVolList.add(trabVol);
            }
        }
        return trabVolList;
    }
}
