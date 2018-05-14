package com.example.mauro.amplis;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {

    public EditText txtMarca;
    public EditText txtModelo;
    public EditText txtPotencia;
    public EditText txtDescripcion;

    public Button btnModificar;

    public boolean flag;

    private SQLiteDatabase db;
    private EquiposSQLiteHelper usdbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        txtMarca = (EditText) findViewById(R.id.tMarca);
        txtModelo = (EditText) findViewById(R.id.tModelo);
        txtPotencia = (EditText) findViewById(R.id.tPotencia);
        txtDescripcion = (EditText) findViewById(R.id.tDescripcion);
        btnModificar = (Button) findViewById(R.id.btnModificar);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this, "DBEquipos", null, 1);
        db = usdbh.getWritableDatabase();

        //Recuperamos la información pasada en el intent
        Bundle bundleB = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        final int posicion =  bundleB.getInt("valor");



        //////////////////////////////////boton modificar/////////////////////////////////////////
        btnModificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String mod = txtModelo.getText().toString();
                String mar = txtMarca.getText().toString();
                String pot = txtPotencia.getText().toString();
                String des = txtDescripcion.getText().toString();
                txtModelo.setText("");
                txtPotencia.setText("");
                txtDescripcion.setText("");
                txtMarca.setText("");

                //////////////////////////////////////////

                if (db != null) {
                    String[] campos = new String[] {"id", "modelo", "marca","imagen", "descripcion"};
                    Cursor c = db.query("Equipos", campos, null, null, null, null, null);


                    if (c.moveToFirst()) {
                        //Recorremos el cursor hasta que no haya más registros

                        int i = 0;
                        do {
                            if (i == posicion) {

                                ContentValues nuevoRegistro = new ContentValues();
                                nuevoRegistro.put("modelo", mod);
                                nuevoRegistro.put("marca", mar);
                                nuevoRegistro.put("imagen", pot);
                                nuevoRegistro.put("descripcion", des);
                                i++;
                                db.update("Equipos", nuevoRegistro, "id=" + i, null);
                                Toast.makeText(ModifyActivity.this,"Elemento MODIFICADO -> " + i,Toast.LENGTH_LONG).show();
                            }
                            i++;
                        } while (c.moveToNext());

                    }
                }


                Intent i = new Intent(ModifyActivity.this, MainActivity.class);
                startActivity(i);
                // Terminamos la activity para que el usuario no pueda volver para atrás con el botón de back
                finish();

            }
        });
    }


}
