package com.pi.fatec.azulapplication.Bean;

/**
 * Created by murilo on 11/06/17.
 */

public class Edicao {
    private int codigoEdicao;
    private String dataInicio;
    private String dataFim;
    private String validade;

    public int getCodigoEdicao() {
        return codigoEdicao;
    }

    public void setCodigoEdicao(int codigoEdicao) {
        this.codigoEdicao = codigoEdicao;
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
}
