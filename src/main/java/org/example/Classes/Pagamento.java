package org.example.Classes;

public class Pagamento {


    private String formPag;
    private String cpf;
    private String doacaoIdTrans;
    private String historicoCompraIdHistorico;


    public String getFormPag() {
        return formPag;
    }

    public void setFormPag(String formPag) {
        this.formPag = formPag;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDoacaoIdTrans() {
        return doacaoIdTrans;
    }

    public void setDoacaoIdTrans(String doacaoIdTrans) {
        this.doacaoIdTrans = doacaoIdTrans;
    }

    public String getHistoricoCompraIdHistorico() {
        return historicoCompraIdHistorico;
    }

    public void setHistoricoCompraIdHistorico(String historicoCompraIdHistorico) {
        this.historicoCompraIdHistorico = historicoCompraIdHistorico;
    }
}
