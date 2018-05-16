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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private TextView txtMarca;
    private EditText txtModelo;
    private EditText txtPotencia;
    private EditText txtDescripcion;
    private Toolbar toolbar;
    private Button btnInsertar;

    private SQLiteDatabase db;
    private EquiposSQLiteHelper usdbh;

    String mod;
    String mar;
    String pot;
    String des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Obtenemos las referencias a los controles
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        Spinner cmbOpciones = (Spinner) findViewById(R.id.CmbOpciones);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.valores_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewTheme(R.);
        cmbOpciones.setAdapter(adapter);

        txtMarca = (TextView) findViewById(R.id.txtMarca);
        txtModelo = (EditText) findViewById(R.id.txtModelo);
        txtPotencia = (EditText)findViewById(R.id.editPotencia);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        btnInsertar = (Button) findViewById(R.id.btnInsertar);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this, "DBEquipos", null, 1);

        db = usdbh.getWritableDatabase();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //Recuperamos los valores de los campos de texto
                mod = txtModelo.getText().toString();
                mar = txtMarca.getText().toString();
                pot = txtPotencia.getText().toString();
                des = txtDescripcion.getText().toString();
                txtMarca.setText("");
                txtModelo.setText("");
                txtPotencia.setText("");
                txtDescripcion.setText("");

                String[] campos = new String[]{"id"};
                Cursor c = db.query("Equipos", campos, null, null, null, null, null);
                int k = c.getCount();
                k++;
                //Alternativa 2: método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("id", k);
                nuevoRegistro.put("modelo", mod);
                nuevoRegistro.put("marca", mar);
                nuevoRegistro.put("potencia", pot);
                nuevoRegistro.put("descripcion", des);
                db.insert("Equipos", null, nuevoRegistro);

                Toast.makeText(AddActivity.this,"Elemento INGRESADO -> " + k,Toast.LENGTH_LONG).show();

                // Start the next activity
                Intent mainIntent = new Intent().setClass(AddActivity.this, MainActivity.class);
                startActivity(mainIntent);

                finish();
            }
        });


        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                        txtMarca.setText(parent.getItemAtPosition(position).toString());
               /*         switch(parent.getItemAtPosition(position).toString()) {
                            case "Fender":
                                im = R.mipmap.ic_fender;
                                break;
                            case "Marshall":
                                im = R.mipmap.ic_fender;
                                break;
                            case "Vox":
                                im = R.mipmap.ic_vox;
                                break;
                            case "Peavey":
                                im = R.mipmap.ic_peavey;
                                break;
                            case "Generico":
                                im = R.mipmap.ic_welcome;
                                break;
                                default: im = R.drawable.ic_nota;
                        }*/
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        txtMarca.setText("");
                    }
                });
    }
}
