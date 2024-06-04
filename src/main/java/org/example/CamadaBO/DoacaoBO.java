package org.example.CamadaBO;

import org.example.Classes.Doacao;
import org.example.db.DoacaoDAO;
import org.example.exception.DataAccessException;

import java.util.List;

public class DoacaoBO {

    private DoacaoDAO doacaoDAO;

    public DoacaoBO() {
        this.doacaoDAO = new DoacaoDAO();
    }

    public void inserirDoacao(Doacao doacao) throws DataAccessException {
        try {
            if (doacaoDAO.selecionarPorId(doacao.getIdTrans()) == null) {
                doacaoDAO.inserir(doacao);
            } else {
                throw new DataAccessException("Doação com este ID já existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao inserir doação: " + e.getMessage());
        }
    }

    public void deletarDoacao(String idTrans) throws DataAccessException {
        try {
            if (doacaoDAO.selecionarPorId(idTrans) != null) {
                doacaoDAO.deletar(idTrans);
            } else {
                throw new DataAccessException("Doação com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao deletar doação: " + e.getMessage());
        }
    }

    public void alterarDoacao(Doacao doacao) throws DataAccessException {
        try {
            if (doacaoDAO.selecionarPorId(doacao.getIdTrans()) != null) {
                doacaoDAO.alterar(doacao);
            } else {
                throw new DataAccessException("Doação com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao alterar doação: " + e.getMessage());
        }
    }

    public List<Doacao> listarTodasDoacoes() throws DataAccessException {
        try {
            return doacaoDAO.selecionarTodos();
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao listar todas as doações: " + e.getMessage());
        }
    }

    public Doacao selecionarDoacaoPorId(String idTrans) throws DataAccessException {
        try {
            Doacao doacao = doacaoDAO.selecionarPorId(idTrans);
            if (doacao != null) {
                return doacao;
            } else {
                throw new DataAccessException("Doação com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao selecionar doação por ID: " + e.getMessage());
        }
    }
}