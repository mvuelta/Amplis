package com.example.mauro.amplis;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CodigoFragment extends Fragment {

    public TextView id;
    public ImageView marca;

    private SQLiteDatabase db;
    private EquiposSQLiteHelper usdbh;

    public static CodigoFragment newInstance() {
        CodigoFragment fragment = new CodigoFragment();
        return fragment;
    }

    public CodigoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        id = (TextView) getView().findViewById(R.id.tCodigo);
        //marca = (ImageView) getView().findViewById(R.id.tBrand);

        //Abrimos la base de datos 'DBEquipos' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this.getActivity(), "DBEquipos", null, 1);
        db = usdbh.getWritableDatabase();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo, container, false);
    }

}
