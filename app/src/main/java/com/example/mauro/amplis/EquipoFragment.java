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
import android.widget.Toast;


public class EquipoFragment extends Fragment {

    public TextView modelo;
    public TextView marca;
    public TextView potencia;

    public String mod;
    public String mar;
    public String pot;

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


        modelo = (TextView) getView().findViewById(R.id.tModel);
        marca = (TextView) getView().findViewById(R.id.tBrand);
        potencia = (TextView) getView().findViewById(R.id.tPower);


        SharedPreferences prefs = this.getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        int posicion = prefs.getInt("posicion", -1);


        //Abrimos la base de datos 'DBEquipos' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this.getActivity(), "DBEquipos", null, 1);
        db = usdbh.getWritableDatabase();

        if(db != null) {
            String[] campos = new String[] {"id", "modelo", "marca", "imagen", "descripcion"};
            //String[] args = new String[] {Integer.toString(posicion)};
            Cursor c = db.query("Equipos", campos, null, null, null, null, null);

            if(c.moveToFirst())
            {
                int k = 0;
                do {
                    if (k == posicion) {

                        //amplisAux[0].setId(c.getInt(0));
                        mod = c.getString(1);
                        mar = c.getString(2);
                        pot = c.getString(3);

                    }
                    k++;
                } while (c.moveToNext());
            }

        }


        modelo.setText(mod);
        marca.setText(mar);
        potencia.setText(pot);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo, container, false);
    }

}
