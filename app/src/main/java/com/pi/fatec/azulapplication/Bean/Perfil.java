package com.pi.fatec.azulapplication.Bean;

/**
 * Created by Andre Neves on 28/06/2017.
 */

public class Perfil {
    int codigo;
    String nomeUsuario;
    String funcao;
    String registro;
    String nomeFuncionario;
    String senha;
    String status;
    String email;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
/*
{
  "$id": "1",
  "codigo": 3,
  "nomeUsuario": "andre.neves",
  "funcao": "Chefe",
  "registro": "0000003",
  "nomeFuncionario": "André Neves da Silva",
  "senha": "1234",
  "status": "T",
  "email": "andre.neves@azul.com.br"
}
 */