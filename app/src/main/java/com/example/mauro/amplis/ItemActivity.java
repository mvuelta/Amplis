package com.example.mauro.amplis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        final TextView marca = (TextView) findViewById(R.id.textMarca);
        final TextView modelo = (TextView) findViewById(R.id.textModelo);
        final TextView potencia = (TextView) findViewById(R.id.textPotencia);
        final TextView descripcion = (TextView) findViewById(R.id.textDescripcion);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            marca.setText("MARCA:\n\n" + parametros.getString("marca"));
            modelo.setText("MODELO:\n\n" + parametros.getString("modelo"));
            potencia.setText("POTENCIA\n\n" + parametros.getInt("potencia") + " Watts");
            descripcion.setText("BREVE DESCRIPCION:\n\n" + parametros.getString("descripcion"));
        }
    }
}
