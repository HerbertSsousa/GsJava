package org.example.CamadaBO;

import org.example.Classes.TrabVol;
import org.example.db.TrabVolDAO;
import org.example.exception.DataAccessException;

import java.sql.SQLException;
import java.util.List;

public class TrabVolBO {

    private final TrabVolDAO trabVolDAO;

    public TrabVolBO() {
        this.trabVolDAO = new TrabVolDAO();
    }

    public void inserirTrabVol(TrabVol trabVol) throws DataAccessException {
        try {
            trabVolDAO.inserir(trabVol);
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao inserir trabalho voluntário: " + e.getMessage());
        }
    }

    public void deletarTrabVol(String idEmpregado) throws DataAccessException {
        try {
            trabVolDAO.deletar(idEmpregado);
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar trabalho voluntário: " + e.getMessage());
        }
    }

    public void alterarTrabVol(TrabVol trabVol) throws DataAccessException {
        try {
            trabVolDAO.alterar(trabVol);
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao alterar trabalho voluntário: " + e.getMessage());
        }
    }

    public TrabVol selecionarTrabVolPorId(String idEmpregado) throws DataAccessException {
        try {
            return trabVolDAO.selecionarPorId(idEmpregado);
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao selecionar trabalho voluntário por ID: " + e.getMessage());
        }
    }

    public List<TrabVol> listarTodosTrabVols() throws DataAccessException {
        try {
            return trabVolDAO.selecionarTodos();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao listar todos os trabalhos voluntários: " + e.getMessage());
        }
    }
}