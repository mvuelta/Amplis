package com.example.mauro.amplis;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EquipoFragment extends Fragment {

    public TextView tModelo;
    public TextView tMarca;

    private String mod;
    private String mar;

    private SQLiteDatabase db;
    private EquiposSQLiteHelper usdbh;

    public static EquipoFragment newInstance() {
        EquipoFragment fragment = new EquipoFragment();
        return fragment;
    }

    public EquipoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        tModelo = (TextView) getView().findViewById(R.id.tModelo);
        tMarca = (TextView) getView().findViewById(R.id.tBrand);


        SharedPreferences prefs = this.getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        int posicion = prefs.getInt("posicion", -1);


        //Abrimos la base de datos 'DBEquipos' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this.getActivity(), "DBEquipos", null, 1);
        db = usdbh.getWritableDatabase();

        if(db != null) {
            String[] campos = new String[] {"modelo", "marca"};
            Cursor c = db.query("Equipos", campos, null, null, null, null, null);

            if(c.moveToFirst())
            {
                int k = 0;
                do {
                    if (k == posicion) {
                        mod = c.getString(0);
                        mar = c.getString(1);
                    }
                    k++;
                } while (c.moveToNext());
            }

        }


        tModelo.setText("Modelo: " + mod);
        tMarca.setText("Marca: "+ mar);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo, container, false);
    }

}
