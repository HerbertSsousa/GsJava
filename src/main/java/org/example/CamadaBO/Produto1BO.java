package org.example.CamadaBO;

import org.example.Classes.Produto1;
import org.example.db.Produto1DAO;
import org.example.exception.DataAccessException;

import java.util.List;

public class Produto1BO {

    private final Produto1DAO produto1DAO;

    public Produto1BO() {
        this.produto1DAO = new Produto1DAO();
    }

    public void inserirProduto(Produto1 produto) throws DataAccessException {
        produto1DAO.inserir(produto);
    }

    public void deletarProduto(String idProd) throws DataAccessException {
        produto1DAO.deletar(idProd);
    }

    public void alterarProduto(Produto1 produto) throws DataAccessException {
        produto1DAO.alterar(produto);
    }

    public Produto1 selecionarProdutoPorId(String idProd) throws DataAccessException {
        return produto1DAO.selecionarPorId(idProd);
    }

    public List<Produto1> listarTodosProdutos() throws DataAccessException {
        return produto1DAO.selecionarTodos();
    }
}