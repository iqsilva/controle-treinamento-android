package com.pi.fatec.azulapplication;

/**
 * Created by Andre Neves on 28/06/2017.
 */

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pi.fatec.azulapplication.Bean.Perfil;
import com.pi.fatec.azulapplication.Util.ConnectionClass;
import com.pi.fatec.azulapplication.Util.SdHelper;
import com.pi.fatec.azulapplication.Util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class PerfilFragment  extends Fragment{
 // TODO: Rename parameter arguments, choose names that match
 // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
 private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv;

    private PerfilFragment.OnFragmentInteractionListener mListener;
    SdHelper sd;
    /*
    int result = 0;
            try {
                Connection con = connectionClass.CONN();
                String query = "INSERT INTO participa VALUES('E',"+EdicaoID+","+userID+")";
                PreparedStatement pstmt = con.prepareStatement(query);
                result = pstmt.executeUpdate();
                if(result == 1){
                    Toast.makeText(getApplicationContext(), "Solicitação feita com sucesso", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
     */

    public void AceitarButton(View v)
    {
        Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.erroPerfil), Toast.LENGTH_SHORT).show();
        TextView txtEmail;
       TextView txtsenha;
       TextView txtsenha2;
      txtEmail = (TextView)       v.findViewById(R.id.editEmail);
      txtsenha = (TextView)       v.findViewById(R.id.editSenha);
      txtsenha2 = (TextView)      v.findViewById(R.id.editSenha2);
      txtEmail.setHint("YEY");
    }

    public void senhabanco(String senha){
        ConnectionClass connectionClass;
        connectionClass = new ConnectionClass();
        int result = 0;
        try {
            Connection con = connectionClass.CONN();
            String query = "update Funcionario set senha = '"+senha+"' where Cod_Func = "+sd.getUserID();
            PreparedStatement pstmt = con.prepareStatement(query);
            result = pstmt.executeUpdate();
            if(result == 1){
                //Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_sucess), Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void emailbanco(String email){
        ConnectionClass connectionClass;
        connectionClass = new ConnectionClass();
        int result = 0;
        try {
            Connection con = connectionClass.CONN();
            String query = "update Funcionario set Email_Func = '"+email+"' where Cod_Func = "+sd.getUserID();
            PreparedStatement pstmt = con.prepareStatement(query);
            result = pstmt.executeUpdate();
            if(result == 1){
                //Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_sucess), Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_sucess), Toast.LENGTH_SHORT).show();
    }

    public void teste(){
        Button b = (Button)  getView().findViewById(R.id.Button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtEmail;
                TextView txtsenha;
                TextView txtsenha2;
                txtEmail = (TextView)       getView().findViewById(R.id.editEmail);
                txtsenha = (TextView)       getView().findViewById(R.id.editSenha);
                txtsenha2 = (TextView)      getView().findViewById(R.id.editSenha2);
                String email,senha1,senha2;
                email = txtEmail.getText().toString();
                senha1 = txtsenha.getText().toString();
                senha2 = txtsenha2.getText().toString();
                boolean pass,mail,error;
                error=false;
                pass=false;
                mail=false;

                if (email.isEmpty() && senha1.isEmpty() && senha2.isEmpty()) Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_errornull), Toast.LENGTH_SHORT).show();

                if (!email.isEmpty()){
                    if (email.contains("@azul.com.br")){
                        mail = true;
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_erroremail), Toast.LENGTH_SHORT).show();
                        error=true;
                    }

                }

                if (!senha2.isEmpty()){
                    if (!senha1.isEmpty()){
                        if (senha1.equals(senha2)){
                            pass=true;
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_errorsenha), Toast.LENGTH_SHORT).show();
                            error=true;

                        }

                    }else {
                        Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_errorsenha), Toast.LENGTH_SHORT).show();
                        error=true;
                    }

                }else if (!senha1.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_errorsenha), Toast.LENGTH_SHORT).show();
                    error=true;
                }

                if (error==false) {
                    if (pass == true) {
                        if (mail == true) {
                            emailbanco(email);
                            senhabanco(senha1);
                            Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_sucess), Toast.LENGTH_SHORT).show();
                        } else {
                            senhabanco(senha1);
                            Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_sucess), Toast.LENGTH_SHORT).show();
                        }
                    } else if (mail == true) {
                        emailbanco(email);
                        Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.perfil_sucess), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ///////////////////////////////////////////////////////////////////////////////
    //AQUI BAIXAMOS O JSON MUITO IMPORTANTE TAMBÉM
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PerfilFragment.GetJson download = new PerfilFragment.GetJson();
        download.execute();
        sd = new SdHelper(getActivity().getApplicationContext());
    }
    ///////////////////////////////////////////////////////////////////////////////
    //AQUI CRIAMOS A LIST VIEW MUITO IMPORTANTE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);
        lv = (ListView) view.findViewById(R.id.listPerfil);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PerfilFragment.OnFragmentInteractionListener) {
            mListener = (PerfilFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class GetJson extends AsyncTask<Object, Object, List<Perfil>> {
        @Override
        protected void onPreExecute() { }
        @Override
        protected List<Perfil> doInBackground(Object... params) {
            List<Perfil> perfil;
            Utils util = new Utils();
            perfil = util.getPerfil("http://45.32.171.157:5000/api/azul/funcionarios/"+sd.getUserID());
            return perfil;
        }
        protected void onPostExecute(final List<Perfil> perfil){
            super.onPostExecute(perfil);
            if(perfil == null){
                Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.erroPerfil), Toast.LENGTH_SHORT).show();
            }else{

                PerfilFragment.GetJson.PerfilAdapter adapter = new PerfilFragment.GetJson.PerfilAdapter(getActivity().getApplicationContext(),R.layout.perfil_row,perfil);
                lv.setAdapter(adapter);
                teste();
            }
        }
        //ADAPTER PARA O CURSO E SETAR O CONTEÚDO NO LAYOUT
        public class PerfilAdapter extends ArrayAdapter {
            private List<Perfil> perfilModelList;
            private int resource;
            private LayoutInflater inflater;
            public PerfilAdapter(Context context, int resource, List<Perfil> objects) {
                super(context, resource, objects);
                perfilModelList = objects;
                this.resource = resource;
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                PerfilFragment.GetJson.PerfilAdapter.ViewHolder holder = null;
                if(convertView == null){
                    holder = new PerfilFragment.GetJson.PerfilAdapter.ViewHolder();
                    convertView = inflater.inflate(resource, null);
                    holder.txtNome      =   (TextView)      convertView.findViewById(R.id.listNome);
                    holder.txtEmail     =   (EditText)      convertView.findViewById(R.id.editEmail);
                    holder.txtsenha     =   (TextView)      convertView.findViewById(R.id.editSenha);
                    holder.txtsenha2    =   (TextView)      convertView.findViewById(R.id.editSenha2);
                    holder.txtregistro  =   (TextView)      convertView.findViewById(R.id.txtRegistro);
                    holder.txtcargo     =   (TextView)      convertView.findViewById(R.id.txtCargo);
                    holder.txtusuario     =   (TextView)      convertView.findViewById(R.id.txtUsuario);
                    convertView.setTag(holder);
                } else {
                    holder = (PerfilFragment.GetJson.PerfilAdapter.ViewHolder) convertView.getTag();
                }

                holder.txtEmail.setHint(perfilModelList.get(position).getEmail());
                holder.txtNome.setText(perfilModelList.get(position).getNomeFuncionario());
                holder.txtregistro.setText(perfilModelList.get(position).getRegistro());
                holder.txtcargo.setText(perfilModelList.get(position).getFuncao());
                holder.txtusuario.setText(perfilModelList.get(position).getNomeUsuario());

                return convertView;
            }
            class ViewHolder{
                private TextView txtNome;
                private EditText txtEmail;
                private TextView txtsenha;
                private TextView txtsenha2;
                private TextView txtregistro;
                private TextView txtcargo;
                private TextView txtusuario;

            }
        }
    }

}
