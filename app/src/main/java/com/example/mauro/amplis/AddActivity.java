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

    private SQLiteDatabase db;
    private EquiposSQLiteHelper usdbh;

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

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this, "DBEquipos", null, 1);

        db = usdbh.getWritableDatabase();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //Recuperamos los valores de los campos de texto
                String mod = txtModelo.getText().toString();
                String mar = txtMarca.getText().toString();
                String pot = txtPotencia.getText().toString();
                String des = txtDescripcion.getText().toString();
                txtMarca.setText("");
                txtModelo.setText("");
                txtDescripcion.setText("");
                txtPotencia.setText("");

                String[] campos = new String[]{"id"};
                Cursor c = db.query("Equipos", campos, null, null, null, null, null);
                int k = c.getCount();
                k++;
                //Alternativa 2: método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("id", k);
                nuevoRegistro.put("modelo", mod);
                nuevoRegistro.put("marca", mar);
                nuevoRegistro.put("imagen", pot);
                nuevoRegistro.put("descripcion", des);
                db.insert("Equipos", null, nuevoRegistro);

                Toast.makeText(AddActivity.this,"Elemento INGRESADO -> " + k,Toast.LENGTH_LONG).show();

                // Start the next activity
                Intent mainIntent = new Intent().setClass(AddActivity.this, MainActivity.class);
                startActivity(mainIntent);

                finish();
            }
        });
    }
}
