package org.example.CamadaBO;

import org.example.Classes.Cadastro;
import org.example.db.CadastroDAO;
import org.example.exception.DataAccessException;

import java.util.List;

public class CadastroBO {

    private CadastroDAO cadastroDAO;

    public CadastroBO() {
        this.cadastroDAO = new CadastroDAO();
    }

    public void inserirCadastro(Cadastro cadastro) throws DataAccessException {
        try {
            if (cadastroDAO.selecionarPorCpf(cadastro.getCpf()) == null) {
                cadastroDAO.inserir(cadastro);
            } else {
                throw new DataAccessException("Cadastro com este CPF já existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao inserir cadastro: " + e.getMessage());
        }
    }

    public void deletarCadastro(String cpf) throws DataAccessException {
        try {
            if (cadastroDAO.selecionarPorCpf(cpf) != null) {
                cadastroDAO.deletar(cpf);
            } else {
                throw new DataAccessException("Cadastro com este CPF não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao deletar cadastro: " + e.getMessage());
        }
    }

    public void alterarCadastro(Cadastro cadastro) throws DataAccessException {
        try {
            if (cadastroDAO.selecionarPorCpf(cadastro.getCpf()) != null) {
                cadastroDAO.alterar(cadastro);
            } else {
                throw new DataAccessException("Cadastro com este CPF não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao alterar cadastro: " + e.getMessage());
        }
    }

    public List<Cadastro> listarTodosCadastros() throws DataAccessException {
        try {
            return cadastroDAO.selecionarTodos();
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao listar todos os cadastros: " + e.getMessage());
        }
    }

    public Cadastro selecionarCadastroPorCpf(String cpf) throws DataAccessException {
        try {
            Cadastro cadastro = cadastroDAO.selecionarPorCpf(cpf);
            if (cadastro != null) {
                return cadastro;
            } else {
                throw new DataAccessException("Cadastro com este CPF não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao selecionar cadastro por CPF: " + e.getMessage());
        }
    }
}
