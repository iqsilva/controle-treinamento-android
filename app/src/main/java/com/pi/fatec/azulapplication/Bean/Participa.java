package com.pi.fatec.azulapplication.Bean;

/**
 * Created by murilo on 27/06/17.
 */

public class Participa {
    int codParticipa;
    String descricao;
    String dataInicio;
    String dataFim;
    String validade;
    String statusEdicao;
    String nomeFunc;

    public int getCodParticipa() {
        return codParticipa;
    }

    public void setCodParticipa(int codParticipa) {
        this.codParticipa = codParticipa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getStatusEdicao() {
        return statusEdicao;
    }

    public void setStatusEdicao(String statusEdicao) {
        this.statusEdicao = statusEdicao;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }
}
