package com.caiobraz.opcaocompraoferta.core.resource.dto;

import java.io.Serializable;

public class ValidationMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String campo;
    private String mensagem;

    public ValidationMessage() {
    }

    public ValidationMessage(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
