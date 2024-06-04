package org.example.CamadaBO;

import org.example.Classes.Pagamento;
import org.example.db.PagamentoDAO;
import org.example.exception.DataAccessException;

import java.util.List;

public class PagamentoBO {

    private final PagamentoDAO pagamentoDAO;

    public PagamentoBO() {
        this.pagamentoDAO = new PagamentoDAO();
    }

    public void inserirPagamento(Pagamento pagamento) throws DataAccessException {
        pagamentoDAO.inserir(pagamento);
    }

    public void deletarPagamento(String cpf) throws DataAccessException {
        pagamentoDAO.deletar(cpf);
    }

    public void alterarPagamento(Pagamento pagamento) throws DataAccessException {
        pagamentoDAO.alterar(pagamento);
    }

    public Pagamento selecionarPagamentoPorCpf(String cpf) throws DataAccessException {
        return pagamentoDAO.selecionarPorCpf(cpf);
    }

    public List<Pagamento> selecionarTodosPagamentos() throws DataAccessException {
        return pagamentoDAO.selecionarTodos();
    }
}