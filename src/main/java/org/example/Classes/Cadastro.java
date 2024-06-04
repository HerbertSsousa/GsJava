package org.example.Classes;

public class Cadastro {

    private String cpf;
    private String email;
    private String nomeCompleto;
    private String senha;
    private String viaCep;
    private String loginUsuario;
    private String produtoIdProd;
    private String doacaoIdTrans;
    private String trabVolIdEmpregado;
    private String carrinhoIdCarrinho;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getViaCep() {
        return viaCep;
    }

    public void setViaCep(String viaCep) {
        this.viaCep = viaCep;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getProdutoIdProd() {
        return produtoIdProd;
    }

    public void setProdutoIdProd(String produtoIdProd) {
        this.produtoIdProd = produtoIdProd;
    }

    public String getDoacaoIdTrans() {
        return doacaoIdTrans;
    }

    public void setDoacaoIdTrans(String doacaoIdTrans) {
        this.doacaoIdTrans = doacaoIdTrans;
    }

    public String getTrabVolIdEmpregado() {
        return trabVolIdEmpregado;
    }

    public void setTrabVolIdEmpregado(String trabVolIdEmpregado) {
        this.trabVolIdEmpregado = trabVolIdEmpregado;
    }

    public String getCarrinhoIdCarrinho() {
        return carrinhoIdCarrinho;
    }

    public void setCarrinhoIdCarrinho(String carrinhoIdCarrinho) {
        this.carrinhoIdCarrinho = carrinhoIdCarrinho;
    }


    public Cadastro(String cpf, String email, String nomeCompleto, String senha, String viaCep, String loginUsuario, String produtoIdProd, String doacaoIdTrans, String trabVolIdEmpregado, String carrinhoIdCarrinho) {
        this.cpf = cpf;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
        this.viaCep = viaCep;
        this.loginUsuario = loginUsuario;
        this.produtoIdProd = produtoIdProd;
        this.doacaoIdTrans = doacaoIdTrans;
        this.trabVolIdEmpregado = trabVolIdEmpregado;
        this.carrinhoIdCarrinho = carrinhoIdCarrinho;
    }
}
