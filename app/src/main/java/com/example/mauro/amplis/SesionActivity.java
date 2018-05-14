package com.example.mauro.amplis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SesionActivity extends AppCompatActivity {

    public EditText login;
    public Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        login = findViewById(R.id.txtLogin);
        btnIniciar = findViewById(R.id.btnIniciar);

        final SharedPreferences prefs = getSharedPreferences("USUARIO", Context.MODE_PRIVATE);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creo un SharedPreferences editor
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("nombre", login.getText().toString());
                editor.apply();

                Intent mainIntent = new Intent(SesionActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }

    public static class DescripcionFragment extends Fragment {

        public TextView descripcion;
        public TextView marca;
        public TextView potencia;

        private SQLiteDatabase db;
        private EquiposSQLiteHelper usdbh;

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


     /*       modelo = (TextView) getView().findViewById(R.id.tModel);
            marca = (TextView) getView().findViewById(R.id.tBrand);
            potencia = (TextView) getView().findViewById(R.id.tPower);*/

            //Abrimos la base de datos 'DBEquipos' en modo escritura
            final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this.getActivity(), "DBEquipos", null, 1);
            db = usdbh.getWritableDatabase();



        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_descripcion, container, false);
        }

    }
}
