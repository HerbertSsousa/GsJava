package org.example.CamadaBO;



import org.example.Classes.Login;
import org.example.db.LoginDAO;
import org.example.exception.DataAccessException;

public class LoginBO {

    private LoginDAO loginDAO;

    public LoginBO() {
        this.loginDAO = new LoginDAO();
    }

    public void inserirLogin(Login login) throws DataAccessException {
        try {
            if (loginDAO.selecionarPorUsuario(login.getUsuario()) == null) {
                loginDAO.inserir(login);
            } else {
                throw new DataAccessException("Login com este usuário já existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao inserir login: " + e.getMessage());
        }
    }

    public void deletarLogin(String usuario) throws DataAccessException {
        try {
            if (loginDAO.selecionarPorUsuario(usuario) != null) {
                loginDAO.deletar(usuario);
            } else {
                throw new DataAccessException("Login com este usuário não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao deletar login: " + e.getMessage());
        }
    }

    public void alterarSenha(String usuario, String novaSenha) throws DataAccessException {
        try {
            Login login = loginDAO.selecionarPorUsuario(usuario);
            if (login != null) {
                login.setSenha(novaSenha);
                loginDAO.alterar(login);
            } else {
                throw new DataAccessException("Login com este usuário não existe.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao alterar senha: " + e.getMessage());
        }
    }

    public boolean validarLogin(String usuario, String senha) throws DataAccessException {
        try {
            Login login = loginDAO.selecionarPorUsuario(usuario);
            return login != null && login.getSenha().equals(senha);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao validar login: " + e.getMessage());
        }
    }
}