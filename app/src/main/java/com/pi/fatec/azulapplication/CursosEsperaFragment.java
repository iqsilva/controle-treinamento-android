package com.pi.fatec.azulapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pi.fatec.azulapplication.Bean.Curso;
import com.pi.fatec.azulapplication.Bean.Edicao;
import com.pi.fatec.azulapplication.Bean.Participa;
import com.pi.fatec.azulapplication.Util.SdHelper;
import com.pi.fatec.azulapplication.Util.Utils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CursosEsperaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CursosEsperaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CursosEsperaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv;

    private OnFragmentInteractionListener mListener;

    SdHelper sd;

    public CursosEsperaFragment() {
        // Required empty public constructor
    }

    public static CursosEsperaFragment newInstance(String param1, String param2) {
        CursosEsperaFragment fragment = new CursosEsperaFragment();
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
        GetJson download = new GetJson();
        download.execute();
        sd = new SdHelper(getActivity().getApplicationContext());
    }
    ///////////////////////////////////////////////////////////////////////////////
    //AQUI CRIAMOS A LIST VIEW MUITO IMPORTANTE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos_espera,container,false);
        lv = (ListView) view.findViewById(R.id.listCursoEspera);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    private class GetJson extends AsyncTask<Object, Object, List<Participa>> {
        @Override
        protected void onPreExecute() { }
        @Override
        protected List<Participa> doInBackground(Object... params) {
            List<Participa> espera;
            Utils util = new Utils();
            espera = util.getCursosEspera("http://45.32.171.157:5000/api/azul/funcionarios/espera/"+sd.getUserID());
            return espera;
        }
        protected void onPostExecute(final List<Participa> cursoEspera){
            super.onPostExecute(cursoEspera);
            if(cursoEspera == null){
                Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.erroCursoEspera), Toast.LENGTH_SHORT).show();
            }else{
                CursoEsperaAdapter adapter = new CursoEsperaAdapter(getActivity().getApplicationContext(),R.layout.cursos_espera_row,cursoEspera);
                lv.setAdapter(adapter);
            }
        }
        //ADAPTER PARA O CURSO E SETAR O CONTEÚDO NO LAYOUT
        public class CursoEsperaAdapter extends ArrayAdapter {
            private List<Participa> participaModelList;
            private int resource;
            private LayoutInflater inflater;
            public CursoEsperaAdapter(Context context, int resource, List<Participa> objects) {
                super(context, resource, objects);
                participaModelList = objects;
                this.resource = resource;
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                String data;
                if(convertView == null){
                    holder = new ViewHolder();
                    convertView = inflater.inflate(resource, null);
                    holder.tvTitulo = (TextView)convertView.findViewById(R.id.txtCursoEspera) ;
                    holder.tvDataInicio = (TextView)convertView.findViewById(R.id.txtDataIncioEspera) ;
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                data = participaModelList.get(position).getDataInicio();
                holder.tvTitulo.setText(participaModelList.get(position).getDescricao());
                holder.tvDataInicio.setText(getActivity().getApplicationContext().getString(R.string.dataInicioCursoEspera)+data.substring(0,data.length()-12));
                return convertView;
            }
            class ViewHolder{
                private TextView tvTitulo;
                private TextView tvDataInicio;
            }
        }
    }
}
