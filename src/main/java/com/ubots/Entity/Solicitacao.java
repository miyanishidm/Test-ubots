package com.ubots.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Solicitacao {

    @Id
    private Long id;
    private String assunto;
    private String solicitacao;

    public Solicitacao(String assunto) {
        this.assunto = assunto;
    }

    public Solicitacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }
}
