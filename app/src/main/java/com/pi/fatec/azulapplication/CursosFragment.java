package com.pi.fatec.azulapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pi.fatec.azulapplication.Bean.Curso;
import com.pi.fatec.azulapplication.Util.ConnectionClass;
import com.pi.fatec.azulapplication.Util.Utils;

import java.util.ArrayList;
import java.util.List;

public class CursosFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lv;
    private OnFragmentInteractionListener mListener;

    public CursosFragment() {}

    public static CursosFragment newInstance(String param1, String param2) {
        CursosFragment fragment = new CursosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetJson download = new GetJson();
        download.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos,container,false);
        lv = (ListView) view.findViewById(R.id.listCurso);
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
        void onFragmentInteraction(Uri uri);
    }

    private class GetJson extends AsyncTask<Object, Object, List<Curso>> {
        @Override
        protected void onPreExecute() { }
        @Override
        protected List<Curso> doInBackground(Object... params) {
            List<Curso> cursos;
            Utils util = new Utils();
            cursos = util.getCursos("http://45.32.171.157:5000/api/azul/cursos");
            return cursos;
        }
        protected void onPostExecute(final List<Curso> curso){
            super.onPostExecute(curso);
            CursoAdapter adapter = new CursoAdapter(getActivity().getApplicationContext(),R.layout.cursos_row,curso);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Curso modelCursos = curso.get(position);
                    Intent intent = new Intent(getActivity(), EdicaoActivity.class);
                    intent.putExtra("modelCursos", new Gson().toJson(modelCursos));
                    startActivity(intent);
                }
            });
        }
    }

    //ADAPTER PARA O CURSO E SETAR O CONTEÚDO NO LAYOUT
    public class CursoAdapter extends ArrayAdapter{
        private List<Curso> cursoModelList;
        private int resource;
        private LayoutInflater inflater;
        public CursoAdapter(Context context, int resource, List<Curso> objects) {
            super(context, resource, objects);
            cursoModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);
                holder.tvTitulo = (TextView)convertView.findViewById(R.id.listCursoTitulo) ;
                holder.tvCargaHoraria = (TextView) convertView.findViewById(R.id.listCursoCarga);
                holder.tvVigencia = (TextView) convertView.findViewById(R.id.listCursoVigencia);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvTitulo.setText(cursoModelList.get(position).getTitulo());
            holder.tvCargaHoraria.setText(getActivity().getApplicationContext().getString(R.string.cargaHoraria)+cursoModelList.get(position).getCargaHoraria());
            holder.tvVigencia.setText(getActivity().getApplicationContext().getString(R.string.vigencia)+cursoModelList.get(position).getVigencia());
            return convertView;
        }
        class ViewHolder{
            private TextView tvCodigo;
            private TextView tvTitulo;
            private TextView tvVigencia;
            private TextView tvCargaHoraria;
        }
    }
}
