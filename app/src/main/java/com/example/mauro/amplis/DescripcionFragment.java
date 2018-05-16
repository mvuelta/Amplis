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
import android.widget.ImageView;
import android.widget.TextView;

public class DescripcionFragment extends Fragment {

    private TextView descripcion;
    private ImageView imagen;

    private SQLiteDatabase db;
    private EquiposSQLiteHelper usdbh;

    Equipos amplisAux;

    public static DescripcionFragment newInstance() {
        DescripcionFragment fragment = new DescripcionFragment();
        return fragment;
    }

    public DescripcionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        amplisAux = new Equipos();
        descripcion = (TextView) getView().findViewById(R.id.textoDescripcion);
        imagen = (ImageView) getView().findViewById(R.id.imLogo);
        //potencia = (TextView) getView().findViewById(R.id.tPower);

        SharedPreferences prefs = this.getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        int posicion = prefs.getInt("posicion", -1);


        //Abrimos la base de datos 'DBEquipos' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this.getActivity(), "DBEquipos", null, 1);
        db = usdbh.getWritableDatabase();

        if(db != null) {
            String[] campos = new String[] {"id", "imagen", "descripcion"};
            Cursor c = db.query("Equipos", campos, null, null, null, null, null);

            if(c.moveToFirst())
            {
                int k = 0;
                do {
                    if (k == posicion) {
                        amplisAux.setId(c.getInt(0));
                        amplisAux.setPotencia(c.getInt(1));
                        amplisAux.setDescripcion(c.getString(2));
                    }
                    k++;
                } while (c.moveToNext());
            }

        }

        descripcion.setText(amplisAux.getDescripcion().toString());
        imagen.setImageResource(amplisAux.getPotencia());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_descripcion, container, false);
    }

}
