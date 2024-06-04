package org.example.CamadaBO;

import org.example.Classes.Carrinho;
import org.example.db.CarrinhoDAO;
import org.example.exception.DataAccessException;

import java.util.List;

public class CarrinhoBO {

    private CarrinhoDAO carrinhoDAO;

    public CarrinhoBO() {
        this.carrinhoDAO = new CarrinhoDAO();
    }

    public void inserirCarrinho(Carrinho carrinho) throws DataAccessException {
        try {
            if (carrinhoDAO.selecionarPorId(carrinho.getIdCarrinho()) == null) {
                carrinhoDAO.inserir(carrinho);
            } else {
                throw new DataAccessException("Carrinho com este ID já existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao inserir carrinho: " + e.getMessage());
        }
    }

    public void deletarCarrinho(String idCarrinho) throws DataAccessException {
        try {
            if (carrinhoDAO.selecionarPorId(idCarrinho) != null) {
                carrinhoDAO.deletar(idCarrinho);
            } else {
                throw new DataAccessException("Carrinho com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao deletar carrinho: " + e.getMessage());
        }
    }

    public void alterarCarrinho(Carrinho carrinho) throws DataAccessException {
        try {
            if (carrinhoDAO.selecionarPorId(carrinho.getIdCarrinho()) != null) {
                carrinhoDAO.alterar(carrinho);
            } else {
                throw new DataAccessException("Carrinho com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao alterar carrinho: " + e.getMessage());
        }
    }

    public List<Carrinho> listarTodosCarrinhos() throws DataAccessException {
        try {
            return carrinhoDAO.selecionarTodos();
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao listar todos os carrinhos: " + e.getMessage());
        }
    }

    public Carrinho selecionarCarrinhoPorId(String idCarrinho) throws DataAccessException {
        try {
            Carrinho carrinho = carrinhoDAO.selecionarPorId(idCarrinho);
            if (carrinho != null) {
                return carrinho;
            } else {
                throw new DataAccessException("Carrinho com este ID não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao selecionar carrinho por ID: " + e.getMessage());
        }
    }
}