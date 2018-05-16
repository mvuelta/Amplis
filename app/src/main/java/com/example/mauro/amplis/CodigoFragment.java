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

public class CodigoFragment extends Fragment {

    public TextView codigo;

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


        codigo = (TextView) getView().findViewById(R.id.tCodigo);

        SharedPreferences prefs = this.getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        int posicion = prefs.getInt("posicion", -1);

        //Abrimos la base de datos 'DBEquipos' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this.getActivity(), "DBEquipos", null, 1);
        db = usdbh.getReadableDatabase();

        if(db != null) {
            String[] campos = new String[] {"id"};
            Cursor c = db.query("Equipos", campos, null, null, null, null, null);

            if(c.moveToFirst())
            {
                int k = 0;
                do {
                    if (k == posicion) {
                        codigo.setText("Codigo = " + c.getString(0));
                    }
                    k++;
                } while (c.moveToNext());
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codigo, container, false);
    }

}
