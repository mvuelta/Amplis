package com.example.mauro.amplis;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        login = findViewById(R.id.txtLogin);
        btnIniciar = findViewById(R.id.btnIniciar);

        EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this, "DBUsuarios", null, 1);
        db = usdbh.getWritableDatabase();

        final SharedPreferences prefs = getSharedPreferences("USUARIO", Context.MODE_PRIVATE);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creo un SharedPreferences editor
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("nombre", login.getText().toString());
                editor.apply();

                ContentValues nuevo = new ContentValues();
                nuevo.put("user", login.getText().toString());
                db.insert("Usuarios", null, nuevo);

                Intent mainIntent = new Intent(SesionActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}