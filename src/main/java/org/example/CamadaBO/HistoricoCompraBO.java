package org.example.CamadaBO;

import org.example.Classes.HistoricoCompra;
import org.example.db.HistoricoCompraDAO;
import org.example.exception.DataAccessException;

import java.util.List;

public class HistoricoCompraBO {

    private final HistoricoCompraDAO historicoCompraDAO;

    public HistoricoCompraBO() {
        this.historicoCompraDAO = new HistoricoCompraDAO();
    }

    public void inserirHistoricoCompra(HistoricoCompra historicoCompra) throws DataAccessException {
        historicoCompraDAO.inserir(historicoCompra);
    }

    public void deletarHistoricoCompra(String idHistorico) throws DataAccessException {
        historicoCompraDAO.deletar(idHistorico);
    }

    public void alterarHistoricoCompra(HistoricoCompra historicoCompra) throws DataAccessException {
        historicoCompraDAO.alterar(historicoCompra);
    }

    public List<HistoricoCompra> selecionarTodosHistoricosCompra() throws DataAccessException {
        return historicoCompraDAO.selecionarTodos();
    }

    public HistoricoCompra selecionarHistoricoCompraPorId(String idHistorico) throws DataAccessException {
        return historicoCompraDAO.selecionarPorId(idHistorico);
    }
}