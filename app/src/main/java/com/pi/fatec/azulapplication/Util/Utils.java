package com.pi.fatec.azulapplication.Util;

import android.util.Log;
import android.widget.ListView;

import com.pi.fatec.azulapplication.Bean.Curso;
import com.pi.fatec.azulapplication.Bean.Edicao;
import com.pi.fatec.azulapplication.Bean.Participa;
import com.pi.fatec.azulapplication.Bean.Perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private List<Curso> cursosList = new ArrayList<Curso>();
    private List<Edicao> edicaoList = new ArrayList<Edicao>();
    private List<Participa> cursoEspera = new ArrayList<Participa>();
    private List<Perfil> perfilList =  new ArrayList<Perfil>();
    private List<Participa> cursoAndamento = new ArrayList<Participa>();


    public List<Perfil> getPerfil(String end){
        List<Perfil> retorno;
        String json;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonPerfil(json);
        return retorno;
    }
    private List<Perfil> parseJsonPerfil(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i=0; i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Perfil perfil = new Perfil();
                perfil.setCodigo(Integer.parseInt(obj.getString("codigo")));
                perfil.setNomeUsuario(obj.getString("nomeUsuario"));
                perfil.setFuncao(obj.getString("funcao"));
                perfil.setRegistro(obj.getString("registro"));
                perfil.setNomeFuncionario(obj.getString("nomeFuncionario"));
                perfil.setSenha(obj.getString("senha"));
                perfil.setStatus(obj.getString("status"));
                perfil.setEmail(obj.getString("email"));
                perfilList.add(perfil);
            }
            return perfilList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
    {
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



    public List<Curso> getCursos(String end){
        List<Curso> retorno;
        String json;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJson(json);
        return retorno;
    }
    private List<Curso> parseJson(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i=0; i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Curso curso = new Curso();
                curso.setCodigo(Integer.parseInt(obj.getString("codigo")));
                curso.setTitulo(obj.getString("titulo"));
                curso.setVigencia(Integer.parseInt(obj.getString("vigencia")));
                curso.setCargaHoraria(Integer.parseInt(obj.getString("cargaHoraria")));
                cursosList.add(curso);
            }
            return cursosList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Edicao> getEdicoes(String end){
        List<Edicao> retorno;
        String json;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonEdicao(json);
        return retorno;
    }
    private List<Edicao> parseJsonEdicao(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i=0; i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Edicao edicao = new Edicao();
                edicao.setCodigoEdicao(Integer.parseInt(obj.getString("codigoEdicao")));
                edicao.setDataInicio(obj.getString("dataInicio"));
                edicao.setDataFim(obj.getString("dataFim"));
                edicao.setValidade(obj.getString("validade"));
                edicaoList.add(edicao);
            }
            return edicaoList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Participa> getCursosEspera(String end){
        List<Participa> retorno;
        String json;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonCursoEspera(json);
        return retorno;
    }
    private List<Participa> parseJsonCursoEspera(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i=0; i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Participa participa = new Participa();
                participa.setCodParticipa(Integer.parseInt(obj.getString("codParticipa")));
                participa.setDataInicio(obj.getString("dataInicio"));
                if(obj.getString("dataFim") == null){
                    participa.setDataFim(" ");
                }else{
                    participa.setDataFim(obj.getString("dataFim"));
                }
                if(obj.getString("validade") == null){
                    participa.setValidade(obj.getString("validade"));
                }else{
                    participa.setValidade(" ");
                }
                participa.setDescricao(obj.getString("descricao"));
                participa.setNomeFunc(obj.getString("nomeFunc"));
                participa.setStatusEdicao(obj.getString("statusEdicao"));
                cursoEspera.add(participa);
            }
            return cursoEspera;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Participa> getCursosAndamento(String end){
        List<Participa> retorno;
        String json;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonCursoAndamento(json);
        return retorno;
    }
    private List<Participa> parseJsonCursoAndamento(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i=0; i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Participa participa = new Participa();
                participa.setCodParticipa(Integer.parseInt(obj.getString("codParticipa")));
                participa.setDataInicio(obj.getString("dataInicio"));
                participa.setDataFim(obj.getString("dataFim"));
                participa.setValidade(obj.getString("validade"));
                participa.setDescricao(obj.getString("descricao"));
                participa.setNomeFunc(obj.getString("nomeFunc"));
                participa.setStatusEdicao(obj.getString("statusEdicao"));
                cursoAndamento.add(participa);
            }
            return cursoAndamento;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Participa> getCursosConcluido(String end){
        List<Participa> retorno;
        String json;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJsonCursoConcluido(json);
        return retorno;
    }
    private List<Participa> parseJsonCursoConcluido(String json){
        try{
            JSONArray array = new JSONArray(json);
            for(int i=0; i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Participa participa = new Participa();
                participa.setCodParticipa(Integer.parseInt(obj.getString("codParticipa")));
                participa.setDataInicio(obj.getString("dataInicio"));
                participa.setDataFim(obj.getString("dataFim"));
                participa.setValidade(obj.getString("validade"));
                participa.setDescricao(obj.getString("descricao"));
                participa.setNomeFunc(obj.getString("nomeFunc"));
                participa.setStatusEdicao(obj.getString("statusEdicao"));
                cursoAndamento.add(participa);
            }
            return cursoAndamento;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}