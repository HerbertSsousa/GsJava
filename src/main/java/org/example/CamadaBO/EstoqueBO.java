package org.example.CamadaBO;

import org.example.Classes.Estoque;
import org.example.db.EstoqueDAO;
import org.example.exception.DataAccessException;

import java.util.List;

public class EstoqueBO {

    private EstoqueDAO estoqueDAO;

    public EstoqueBO() {
        this.estoqueDAO = new EstoqueDAO();
    }

    public void inserirEstoque(Estoque estoque) throws DataAccessException {
        try {
            if (estoqueDAO.selecionarPorId(estoque.getIdProd()) == null) {
                estoqueDAO.inserir(estoque);
            } else {
                throw new DataAccessException("Estoque com este ID já existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao inserir estoque: " + e.getMessage());
        }
    }

    public void deletarEstoque(String idProd) throws DataAccessException {
        try {
            if (estoqueDAO.selecionarPorId(idProd) != null) {
                estoqueDAO.deletar(idProd);
            } else {
                throw new DataAccessException("Estoque com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao deletar estoque: " + e.getMessage());
        }
    }

    public void alterarEstoque(Estoque estoque) throws DataAccessException {
        try {
            if (estoqueDAO.selecionarPorId(estoque.getIdProd()) != null) {
                estoqueDAO.alterar(estoque);
            } else {
                throw new DataAccessException("Estoque com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao alterar estoque: " + e.getMessage());
        }
    }

    public List<Estoque> listarTodosEstoques() throws DataAccessException {
        try {
            return estoqueDAO.selecionarTodos();
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao listar todos os estoques: " + e.getMessage());
        }
    }

    public Estoque selecionarEstoquePorId(String idProd) throws DataAccessException {
        try {
            Estoque estoque = estoqueDAO.selecionarPorId(idProd);
            if (estoque != null) {
                return estoque;
            } else {
                throw new DataAccessException("Estoque com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao selecionar estoque por ID: " + e.getMessage());
        }
    }
}