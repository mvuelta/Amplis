package com.example.mauro.amplis;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    public EditText txtMarca;
    public EditText txtModelo;
    public EditText txtPotencia;
    public EditText txtDescripcion;

    public Button btnInsertar;

    public SQLiteDatabase db;

    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Obtenemos las referencias a los controles
        toolbar = (Toolbar) findViewById(R.id.appbar);

        txtMarca = (EditText) findViewById(R.id.txtMarca);
        txtModelo = (EditText) findViewById(R.id.txtModelo);
        txtPotencia = (EditText) findViewById(R.id.txtPotencia);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        btnInsertar = (Button) findViewById(R.id.btnInsertar);



        btnInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Abrimos la base de datos 'DBUsuarios' en modo escritura
                EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(AddActivity.this, "DBEquipos", null, 1);

                db = usdbh.getWritableDatabase();
                //Recuperamos los valores de los campos de texto
                String mod = txtModelo.getText().toString();
                String mar = txtMarca.getText().toString();
                String pot = txtPotencia.getText().toString();
                String des = txtDescripcion.getText().toString();

                //Alternativa 2: método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("modelo", mod);
                nuevoRegistro.put("marca", mar);
                nuevoRegistro.put("potencia", pot);
                nuevoRegistro.put("descripcion", des);
                db.insert("Equipos", null, nuevoRegistro);
                txtMarca.setText("");
                txtModelo.setText("");
                txtDescripcion.setText("");
                txtPotencia.setText("");

                Toast.makeText(AddActivity.this,"Elemento INGRESADO",Toast.LENGTH_LONG).show();

                // Start the next activity
                Intent mainIntent = new Intent().setClass(AddActivity.this, MainActivity.class);
                startActivity(mainIntent);

                finish();
            }
        });
    }
}
