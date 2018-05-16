package com.example.mauro.amplis;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //int i;

    public TextView user;
    private Spinner cmbOpciones;
    public ListView lstOpciones;
    public Toolbar toolbar;
    AdaptadorEquipos adaptador;
    ArrayList<Equipos> lista;

    private SQLiteDatabase db;
    private EquiposSQLiteHelper usdbh;

    boolean flag = true;
    private Equipos amplisAux;
    private Equipos[] amplis =
            new Equipos[]{
                    new Equipos(1,"DELUXE REVERB 65", "Fender", 65, "De la linea Vintage Reissu, es un amplificador valvular."),
                    new Equipos(2, "40", "Fender", 40, "Equipo de guitarra de la linea Champion.\n" +
                            "Peso: 8.6 kg"),
                    new Equipos(3,"VT40X", "VOX", 40, "- Modelado de Amps\n" +
                            "- Efectos Programables\n" +
                            "- Pre valvular\n" +
                            "- USB"),
                    new Equipos(4,"AC15C1", "Vox", 15, "Linea Custom\n" +
                            "Válvulas 3 x ECC83/12AX7 en previo, 2 x EL84 en etapa\n" +
                            "Panel de control: Input Normal, Top Boost, Volume, Treble, Bass, Reverb, Tremolo\n" +
                            "Peso: 22 kg"),
                    new Equipos(5, "V845", "Vox", 80, "Pedal de efecto Wah-Wah. Analogico."),
                    new Equipos(6,"A5040100", "Marshall", 50, "Cabezal de guitarra valvular\n" +
                            "Válvulas: 4x ECC83 y 4x EL34\n" +
                            "2 canales: Classic Gain y Ultra Gain\n" +
                            "Peso: 24,2 kg"),
                    new Equipos(7, "Título 7", "Prueba 7", 100, "Prueba6"),
                    new Equipos(8, "Título 8", "Prueba 8",  150, "Prueba7"),
                    new Equipos(9,"Título 9", "Prueba 9", 20, "Prueba8")};



    /****************************Inicio OnCreate***********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = new ArrayList<>(); //Instanciamos el ArrayList que va a ir en el adaptador

        //Obtenemos las referencias a los controles
        user = (TextView)findViewById(R.id.txtUser);
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        //Appbar
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);


        //Registro la ListView en el Context Menu
        registerForContextMenu(lstOpciones);

        //Abrimos la base de datos 'DBEquipos' en modo escritura
        final EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this, "DBEquipos", null, 1);
        db = usdbh.getWritableDatabase();
        db = usdbh.getReadableDatabase();

        //cargo el usuario
        final SharedPreferences prefs = getSharedPreferences("USUARIO", Context.MODE_PRIVATE);
        String usuario = prefs.getString("nombre", "Mauro");
        user.setText("Usuario: " + usuario);

        /*---------------------------------------------------------*/
        //INGRESO
        //---Alternativa lista dinamica---
        if(db != null) { /*********Solo si existe base de datos*********/
            //Ingreso la tabla Equipos de la BD en el ListView
            String[] campos = new String[]{"id", "modelo", "marca", "potencia", "descripcion"};
            Cursor c = db.query("Equipos", campos, null, null, null, null, null);
            if (c.moveToFirst()) { //Muestro la BD en la ListView. Sino hay datos cargo el vector definido.
                //Recorremos el cursor hasta que no haya más registros

                do {
                    amplisAux = new Equipos();
                    amplisAux.setId(c.getInt(0));
                    amplisAux.setModelo(c.getString(1));
                    amplisAux.setMarca(c.getString(2));
                    amplisAux.setPotencia(c.getInt(3));
                    amplisAux.setDescripcion(c.getString(4));

                    lista.add(amplisAux);
                } while (c.moveToNext());

                //c.close();
            }
            //flag = false;
            //Constructor del adaptador, paso contexto (actividad desde la que se crea el adaptador) y el array a mostrar
            adaptador = new AdaptadorEquipos(getApplicationContext(), lista);
            lstOpciones.setAdapter(adaptador);
        }

        else { /**********Sino existe la base de datos, cargo un vector definido para mostrar******************/
            int i;
            if (flag) { //Entro si la BD esta vacia
                ContentValues nuevoRegistro = new ContentValues();
                for (i = 0; i < 9; i++) {
                    nuevoRegistro.put("id", amplis[i].getId());
                    nuevoRegistro.put("marca", amplis[i].getMarca());
                    nuevoRegistro.put("modelo", amplis[i].getModelo());
                    nuevoRegistro.put("potencia", amplis[i].getPotencia());
                    nuevoRegistro.put("descripcion", amplis[i].getDescripcion());
                    db.insert("Equipos", null, nuevoRegistro);
                }
                String[] campos = new String[]{"id", "modelo", "marca", "potencia", "descripcion"};
                Cursor c = db.query("Equipos", campos, null, null, null, null, null);
                if (c.moveToFirst()) { //Muestro la BD en la ListView. Sino hay datos cargo el vector definido.
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        amplisAux = new Equipos();
                        amplisAux.setId(c.getInt(0));
                        amplisAux.setModelo(c.getString(1));
                        amplisAux.setMarca(c.getString(2));
                        amplisAux.setPotencia(c.getInt(3));
                        amplisAux.setDescripcion(c.getString(4));

                        lista.add(amplisAux);
                    } while (c.moveToNext());
                }
                flag = false;
                Toast.makeText(MainActivity.this, "Vector cargado en la base de datos!", Toast.LENGTH_LONG).show();
                //Constructor del adaptador, paso contexto (actividad desde la que se crea el adaptador) y el array a mostrar
                adaptador = new AdaptadorEquipos(getApplicationContext(), lista);
                lstOpciones.setAdapter(adaptador);
            }

        }
        /*---------------Fin del ingreso-------------------------*/



        //------------Elijo un item para mostrarlo
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent itemIntent = new Intent().setClass(MainActivity.this, ItemActivity.class);

                //////////////////////////////////utilizo sharedpreference para enviar dato al fragment
                final SharedPreferences pref = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("posicion", position );
                editor.apply();
                /////////////////////////////////////////////////////////////////fin del envio

                startActivity(itemIntent);


            }
        });

    }
    /****************************Fin OnCreate***********************************/



    protected void onResume() {
        super.onResume();

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

/*

        boolean checkbox = pref.getBoolean("opcion1", false);
        boolean a = pref.getBoolean("pref_4", false);
        if (checkbox == true && a == true)
            //login.setTypeface(null, Typeface.BOLD_ITALIC);
        else {
            if (checkbox == true && a != true)
               // login.setTypeface(null, Typeface.BOLD);
            else {
                if (checkbox != true && a == true)
                 //   login.setTypeface(null, Typeface.ITALIC);
                else
                   // login.setTypeface(null, Typeface.NORMAL);
            }
        }
*/


        String rec_nombre = pref.getString("pref_2", "no");
        if (!rec_nombre.equals("no")) {
            //login.setText("");
            //login.append("La agenda es de: " + rec_nombre);
        }


        String color = pref.getString("color", "N");

        if (color.equals("RO")) {
            toolbar.setTitleTextColor(Color.RED);
        }
        if (color.equals("AM")) {
            toolbar.setTitleTextColor(Color.YELLOW);
        }
        if (color.equals("VE")) {
            toolbar.setTitleTextColor(Color.GREEN);
        }


        SharedPreferences.Editor editor = pref.edit();
        editor.clear().apply();
    }




    /***********************Menu Toolbar*****************************/

    //Creo el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //Metodo para consultar las opciones del menu toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sincro:

                return true;
            case R.id.action_nuevo:
                // Start the next activity
                Intent addIntent = new Intent().setClass(MainActivity.this, AddActivity.class);
                startActivity(addIntent);
                finish();
                return true;
            case R.id.action_buscar:

                return true;
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, PreferenciasActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /*********************Fin Menu Toolbar********************************/


    /*********************Inicio del Context Menu*************************/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Seleccione una opcion");
        menu.add(0, v.getId(), 0, "Eliminar");
        menu.add(0, v.getId(), 0, "Modificar");
    }

    @Override // Consulta por el item seleccionado
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        if(item.getTitle()=="Eliminar"){

            if (db != null) {
                String[] campos = new String[] {"id", "modelo","marca","potencia","descripcion"};
                Cursor c = db.query("Equipos", campos, null, null, null, null, null);

                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    int i = 0;
                    do {
                        if (i == info.position) {
                            String valor = c.getString(0);
                            //////borror el dato
                            db.delete("Equipos", "id=" + valor, null);

                            Toast.makeText(getApplicationContext(),"Elemento ELIMINADO -> " + valor,Toast.LENGTH_LONG).show();
                        }
                        i++;
                    } while (c.moveToNext());

                }
            }
            //Remuevo el item de la ListView
            lista.remove(adaptador.getItem(info.position));

            //Constructor del adaptador, paso contexto (actividad desde la que se crea el adaptador) y el array a mostrar
            adaptador = new AdaptadorEquipos(getApplicationContext(), lista);
            lstOpciones.setAdapter(adaptador);

        }
        else if(item.getTitle()=="Modificar"){
            Intent modIntent = new Intent().setClass(MainActivity.this, ModifyActivity.class);

            //Paso la posicion a la avtivity modificar
            Bundle b = new Bundle();
            b.putInt("valor", info.position);
            modIntent.putExtras(b);
            startActivity(modIntent);
            finish();
            }
            else{
            return false;
            }
        return true;
    }

    /*****************************Fin del Context Menu************************************/


}
