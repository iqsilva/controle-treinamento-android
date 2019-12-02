package com.pi.fatec.azulapplication;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pi.fatec.azulapplication.Bean.Participa;
import com.pi.fatec.azulapplication.Util.SdHelper;
import com.pi.fatec.azulapplication.Util.Utils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CursosConcluidoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CursosConcluidoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CursosConcluidoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SdHelper sd;
    private ListView lv;

    public CursosConcluidoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CursosConcluidoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CursosConcluidoFragment newInstance(String param1, String param2) {
        CursosConcluidoFragment fragment = new CursosConcluidoFragment();
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
        sd = new SdHelper(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos_concluido,container,false);
        lv = (ListView) view.findViewById(R.id.listCursoConcluido);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private class GetJson extends AsyncTask<Object, Object, List<Participa>> {
        @Override
        protected void onPreExecute() { }
        @Override
        protected List<Participa> doInBackground(Object... params) {
            List<Participa> concluido;
            Utils util = new Utils();
            concluido = util.getCursosConcluido("http://45.32.171.157:5000/api/azul/funcionarios/concluido/"+sd.getUserID());
            return concluido;
        }
        protected void onPostExecute(final List<Participa> cursoConcluido){
            super.onPostExecute(cursoConcluido);
            if(cursoConcluido == null){
                Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getString(R.string.erroCursoEspera), Toast.LENGTH_SHORT).show();
            }else{
                CursosConcluidoFragment.GetJson.CursoConcluidoAdapter adapter = new CursosConcluidoFragment.GetJson.CursoConcluidoAdapter(getActivity().getApplicationContext(),R.layout.cursos_concluido_row,cursoConcluido);
                lv.setAdapter(adapter);
            }
        }
        //ADAPTER PARA O CURSO E SETAR O CONTEÚDO NO LAYOUT
        public class CursoConcluidoAdapter extends ArrayAdapter {
            private List<Participa> participaModelList;
            private int resource;
            private LayoutInflater inflater;
            public CursoConcluidoAdapter(Context context, int resource, List<Participa> objects) {
                super(context, resource, objects);
                participaModelList = objects;
                this.resource = resource;
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                CursosConcluidoFragment.GetJson.CursoConcluidoAdapter.ViewHolder holder = null;
                String data;
                if(convertView == null){
                    holder = new CursosConcluidoFragment.GetJson.CursoConcluidoAdapter.ViewHolder();
                    convertView = inflater.inflate(resource, null);
                    holder.tvTitulo = (TextView)convertView.findViewById(R.id.txtCursoEspera) ;
                    holder.tvDataInicio = (TextView)convertView.findViewById(R.id.txtDataIncioEspera) ;
                    convertView.setTag(holder);
                } else {
                    holder = (CursosConcluidoFragment.GetJson.CursoConcluidoAdapter.ViewHolder) convertView.getTag();
                }
                data = participaModelList.get(position).getValidade();
                holder.tvTitulo.setText(participaModelList.get(position).getDescricao());
                holder.tvDataInicio.setText(getActivity().getApplicationContext().getString(R.string.dataValidade)+data.substring(0,data.length()-12));
                return convertView;
            }
            class ViewHolder{
                private TextView tvTitulo;
                private TextView tvDataInicio;
            }
        }
    }
}
