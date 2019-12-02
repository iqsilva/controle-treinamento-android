package com.pi.fatec.azulapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pi.fatec.azulapplication.Bean.Curso;
import com.pi.fatec.azulapplication.Bean.Edicao;
import com.pi.fatec.azulapplication.Util.ConnectionClass;
import com.pi.fatec.azulapplication.Util.SdHelper;
import com.pi.fatec.azulapplication.Util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EdicaoActivity extends AppCompatActivity {
    private TextView tvCursoTitulo;
    private static int idCurso;
    Button btSolicitar;
    private ListView lv;
    private static int EdicaoID;
    ConnectionClass connectionClass;
    SdHelper sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);
        tvCursoTitulo = (TextView) findViewById(R.id.tvCursoTitulo);
        lv = (ListView) findViewById(R.id.lstEdicoes);
        // recovering data from MainActivity, sent via intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String json = bundle.getString("modelCursos");// getting the model from MainActivity send via extras
            Curso cursoModel = new Gson().fromJson(json, Curso.class);
            tvCursoTitulo.setText(cursoModel.getTitulo());
            idCurso = cursoModel.getCodigo();
            Log.d("CODIGO DO CURSO:","CODIGO: "+cursoModel.getCodigo());
        }
        connectionClass = new ConnectionClass();
        btSolicitar = (Button) findViewById(R.id.btEdicaoPost);
        btSolicitar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SolicitarCurso();
            }
        });

        GetJson download = new GetJson();
        download.execute();
        sd = new SdHelper(this);
    }

    private void SolicitarCurso(){
        int userID = sd.getUserID();
        if (EdicaoID == 0){
            Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.erroEdicao), Toast.LENGTH_SHORT).show();
        }else{
            int result = 0;
            try {
                Connection con = connectionClass.CONN();
                String query = "INSERT INTO participa VALUES('E',"+EdicaoID+","+userID+")";
                PreparedStatement pstmt = con.prepareStatement(query);
                result = pstmt.executeUpdate();
                if(result == 1){
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.msgEdicaoEfeturada), Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //ResultSet rs = stmt.executeQuery(query);
            finish();
        }
    }

    private class GetJson extends AsyncTask<Object, Object, List<Edicao>> {
        @Override
        protected void onPreExecute() { }
        @Override
        protected List<Edicao> doInBackground(Object... params) {
            List<Edicao> edicoes;
            Utils util = new Utils();
            edicoes = util.getEdicoes("http://45.32.171.157:5000/api/azul/edicoes/"+idCurso);
            return edicoes;
        }
        protected void onPostExecute(final List<Edicao> edicoes){
            super.onPostExecute(edicoes);
            if(edicoes == null){
                Toast.makeText(getApplicationContext(), "Não há edições para este curso no momento.", Toast.LENGTH_SHORT).show();
            }else{
                EdicaoAdapter adapter = new EdicaoAdapter(getApplicationContext(),R.layout.edicoes_row,edicoes);
                lv.setAdapter(adapter);
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Edicao edicaoModel = edicoes.get(position);
                        EdicaoID = edicaoModel.getCodigoEdicao();
                    }
                });
            }
        }
        //ADAPTER PARA O CURSO E SETAR O CONTEÚDO NO LAYOUT
        public class EdicaoAdapter extends ArrayAdapter {
            private List<Edicao> edicaoModelList;
            private int resource;
            private LayoutInflater inflater;
            public EdicaoAdapter(Context context, int resource, List<Edicao> objects) {
                super(context, resource, objects);
                edicaoModelList = objects;
                this.resource = resource;
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                String inicio,termino,validade;
                if(convertView == null){
                    holder = new ViewHolder();
                    convertView = inflater.inflate(resource, null);
                    holder.tvInicioEdicao = (TextView)convertView.findViewById(R.id.tvInicioEdicao) ;
                    holder.tvTerminoEdicao = (TextView) convertView.findViewById(R.id.tvTerminoEdicao);
                    holder.tvValidadeEdicao = (TextView) convertView.findViewById(R.id.tvValidadeEdicao);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                inicio = edicaoModelList.get(position).getDataInicio();
                termino = edicaoModelList.get(position).getDataFim();
                validade = edicaoModelList.get(position).getValidade();

                holder.tvInicioEdicao.setText(getApplicationContext().getString(R.string.inicioEdicao)+inicio.substring(0,inicio.length() -12));
                holder.tvTerminoEdicao.setText(getApplicationContext().getString(R.string.terminoEdicao)+termino.substring(0,termino.length()-12));
                holder.tvValidadeEdicao.setText(getApplicationContext().getString(R.string.validadeEdicao)+validade.substring(0,validade.length()-12));
                return convertView;
            }
            class ViewHolder{
                private TextView tvInicioEdicao;
                private TextView tvTerminoEdicao;
                private TextView tvValidadeEdicao;
            }
        }
    }
}