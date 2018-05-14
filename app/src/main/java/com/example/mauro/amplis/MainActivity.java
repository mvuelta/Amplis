package com.example.mauro.amplis;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int i;
    public boolean flag = true;

    public ListView lstOpciones;
    public Toolbar toolbar;
    public AdaptadorEquipos adaptador;

    public SQLiteDatabase db;
    //public EquiposSQLiteHelper usdbh;

    Equipos[] amplis =
            new Equipos[]{
                    new Equipos("DELUXE REVERB 65", "Fender", 22, "De la linea Vintage Reissu, es un amplificador valvular."),
                    new Equipos("40", "Fender", 40, "Equipo de guitarra de la linea Champion.\n" +
                            "Peso: 8.6 kg"),
                    new Equipos("VT40X", "VOX", 40, "- Modelado de Amps\n" +
                            "- Efectos Programables\n" +
                            "- Pre valvular\n" +
                            "- USB"),
                    new Equipos("AC15C1", "Vox", 15, "Linea Custom\n" +
                            "Válvulas 3 x ECC83/12AX7 en previo, 2 x EL84 en etapa\n" +
                            "Panel de control: Input Normal, Top Boost, Volume, Treble, Bass, Reverb, Tremolo\n" +
                            "Peso: 22 kg"),
                    new Equipos("V845", "Vox", 9, "Pedal de efecto Wah-Wah. Analogico."),
                    new Equipos("A5040100", "Marshall", 100, "Cabezal de guitarra valvular\n" +
                            "Válvulas: 4x ECC83 y 4x EL34\n" +
                            "2 canales: Classic Gain y Ultra Gain\n" +
                            "Peso: 24,2 kg"),
                    new Equipos("Título 7", "Prueba 7", 100, "Prueba6"),
                    new Equipos("Título 8", "Prueba 8", 100, "Prueba7"),
                    new Equipos("Título 9", "Prueba 9", 100, "Prueba8"),
                    new Equipos("Título 10", "Prueba 10", 100, "Prueba9"),
                    new Equipos("Título 11", "Prueba 11", 100, "Prueba10"),
                    new Equipos("Título 12", "Prueba 12", 100, "Prueba11"),
                    new Equipos("Título 13", "Prueba 13", 100, "Prueba12"),
                    new Equipos("Título 14", "Prueba 14", 100, "Prueba13"),
                    new Equipos("Título 15", "Prueba 15", 100, "Prueba14")};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /****************************Inicio OnCreate***********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las referencias a los controles
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        //Appbar
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        //Establecer el PageAdapter del componente ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new AdaptadorFragmentPager(getSupportFragmentManager()));

        //Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        registerForContextMenu(lstOpciones);

        //Abrimos la base de datos 'DBEquipos' en modo escritura
        EquiposSQLiteHelper usdbh = new EquiposSQLiteHelper(this, "DBEquipos", null, 1);
        db = usdbh.getWritableDatabase();
        db = usdbh.getReadableDatabase();

        //Alternativa 2: método insert()

        //Ingreso la tabla Equipos de la BD en el ListView
        if(db != null){
            //db = usdbh.getReadableDatabase(); //Modo lectura
            String[] campos = new String[] {"modelo", "marca", "potencia", "descripcion"};
            Cursor c = db.query("Equipos", campos, null, null, null, null, null);
            if (c.moveToFirst()) { //Muestro la BD en la ListView. Sino hay datos cargo el vector definido.
                i = 0;
                //Recorremos el cursor hasta que no haya más registros
                Equipos[] amplisAux = new Equipos[c.getCount()];
                do {
                    amplisAux[i] = new Equipos();
                    amplisAux[i].setModelo(c.getString(0));
                    amplisAux[i].setMarca(c.getString(1));
                    amplisAux[i].setPotencia(c.getInt(2));
                    amplisAux[i].setDescripcion(c.getString(3));
                    i++;
                } while(c.moveToNext());
                flag = false;
                //Constructor del adaptador, paso contexto (actividad desde la que se crea el adaptador) y el array a mostrar
                adaptador = new AdaptadorEquipos(MainActivity.this, amplisAux);
                lstOpciones.setAdapter(adaptador);
            }
            else
            {
                if(flag){ //Entro si la BD esta vacia
                    ContentValues nuevoRegistro = new ContentValues();
                    for (i = 0; i < 6; i++) {
                        nuevoRegistro.put("marca", amplis[i].getMarca());
                        nuevoRegistro.put("modelo", amplis[i].getModelo());
                        nuevoRegistro.put("potencia", amplis[i].getPotencia());
                        nuevoRegistro.put("descripcion", amplis[i].getDescripcion());
                        db.insert("Equipos", null, nuevoRegistro);
                    }
                    adaptador = new AdaptadorEquipos(MainActivity.this, amplis);
                    lstOpciones.setAdapter(adaptador);
                    Toast.makeText(MainActivity.this, "Vector cargado en la base de datos!", Toast.LENGTH_LONG).show();
                }
            }
        }

        db.close();
        //Elijo un item para mostrarlo
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent itemIntent = new Intent().setClass(MainActivity.this, ItemActivity.class);
                itemIntent.putExtra("marca", ((Equipos)a.getItemAtPosition(position)).getMarca());
                itemIntent.putExtra("modelo", ((Equipos)a.getItemAtPosition(position)).getModelo());
                itemIntent.putExtra("potencia", ((Equipos)a.getItemAtPosition(position)).getPotencia());
                itemIntent.putExtra("descripcion", ((Equipos)a.getItemAtPosition(position)).getDescripcion());
                startActivity(itemIntent);
                //Alternativa 1:


                //Alternativa 2:
                //String opcionSeleccionada =
                //      ((TextView)v.findViewById(R.id.LblTitulo))
                //          .getText().toString();

                //lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });

    }
    /****************************Fin OnCreate***********************************/

    /****************Consulta opciones Menu Toolbar*****************************/
    //Metodo para consultar las opciones del menu toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sincro:
                EquiposSQLiteHelper usdbh =
                        new EquiposSQLiteHelper(this, "DBEquipos", null, 1);
                db = usdbh.getReadableDatabase();
                //Ingreso la tabla Equipos de la BD en el ListView
                if(db != null){
                    String[] campos = new String[] {"modelo", "marca", "potencia", "descripcion"};
                    Cursor c = db.query("Equipos", campos, null, null, null, null, null);
                    if (c.moveToFirst()) { //Muestro la BD en la ListView
                        i = 0;
                        //Recorremos el cursor hasta que no haya más registros
                        Equipos[] amplisAux = new Equipos[c.getCount()];
                        do {
                            //cargar el vector de equipos
                            amplisAux[i] = new Equipos();
                            amplisAux[i].setModelo(c.getString(0));
                            amplisAux[i].setMarca(c.getString(1));
                            amplisAux[i].setPotencia(c.getInt(2));
                            amplisAux[i].setDescripcion(c.getString(3));
                            i++;
                        } while(c.moveToNext());

                        //Constructor del adaptador, paso contexto (actividad desde la que se crea el adaptador) y el array a mostrar
                        adaptador = new AdaptadorEquipos(MainActivity.this, amplisAux);
                        lstOpciones.setAdapter(adaptador);
                    }
                }
                return true;
            case R.id.action_nuevo:
                // Start the next activity
                Intent addIntent = new Intent().setClass(MainActivity.this, AddActivity.class);
                startActivity(addIntent);
                finish();
                return true;
            case R.id.action_buscar:
                TextView vFrag = (TextView) findViewById(R.id.vFrag);
                vFrag.setTextColor(Color.parseColor("#FF0000"));
                toolbar.setTitleTextColor(Color.parseColor("#FF0000"));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this,
                        PreferenciasActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /*********************Fin Menu Toolbar********************************/


    /*********************Inicio del Context Menu*************************/

    @Override   // OnCreate context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Seleccione una opcion");
        menu.add(0, v.getId(), 0, "Eliminar");
        menu.add(0, v.getId(), 0, "Modificar");
    }

    @Override // Consulta por el item seleccionado
    public boolean onContextItemSelected(MenuItem item){

        final AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        if(item.getTitle()=="Eliminar"){

            Toast.makeText(MainActivity.this,"Elemento borrado!",Toast.LENGTH_LONG).show();

            if (db != null) {
                String[] campos = new String[] {"modelo","marca","potencia","descripcion"};
                Cursor c = db.query("Equipos", campos, null, null, null, null, null);

                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    int i = 0;
                    do {
                        if (i == info.position) {
                            String itemeliminar = c.getString(0);

                            //////borror el dato
                            db.delete("Equipos", "modelo=" + itemeliminar  , null); ////
                        }
                        i++;
                    } while (c.moveToNext());

                }
            }
/*

            //Ingreso la tabla Equipos de la BD en el ListView
            if(db != null){
                String[] campos = new String[] {"modelo","marca","potencia","descripcion"};
                Cursor c = db.query("Equipos", campos, null, null, null, null, null);
                if (c.moveToFirst()) { //Muestro la BD en la ListView
                    i = 0;
                    //Recorremos el cursor hasta que no haya más registros
                    Equipos[] amplisAux = new Equipos[c.getCount()];
                    do {
                        //cargar el vector de equipos
                        amplisAux[i] = new Equipos();
                        amplisAux[i].setModelo(c.getString(0));
                        amplisAux[i].setMarca(c.getString(1));
                        amplisAux[i].setPotencia(c.getInt(2));
                        amplisAux[i].setDescripcion(c.getString(3));
                        i++;
                    } while(c.moveToNext());

                    //Constructor del adaptador, paso contexto (actividad desde la que se crea el adaptador) y el array a mostrar
                    adaptador = new AdaptadorEquipos(MainActivity.this, amplisAux);
                    lstOpciones.setAdapter(adaptador);
                }
            }
*/


        }
        else if(item.getTitle()=="Modificar"){
            Toast.makeText(MainActivity.this,"sending sms code",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

    /*****************************Fin del Context Menu************************************/

/*    toolbar.OnMenuItemClickListener()

    toolbar.setOnMenuItemClickListener(
            new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_1:
                    Log.i("Toolbar 2", "Acción Tarjeta 1");
                    break;
                case R.id.action_2:
                    Log.i("Toolbar 2", "Acción Tarjeta 2");
                    break;
            }

            return true;
        }
    });*/

    private void setupViewPager(ViewPager viewPager) {
        AdaptadorFragmentPager adapter = new AdaptadorFragmentPager(getSupportFragmentManager());
        adapter.addFragment(new MarshallFragment(), "CLIENTES");
        adapter.addFragment(new VoxFragment(), "PRODUCTOS");
        //adapter.addFragment(new PedidosFragment(), "PEDIDOS/VENTAS");
        //adapter.addFragment(new ComprasFragment(), "COMPRAS");
        //adapter.addFragment(new CajasFragment(), "CAJA");
        //adapter.addFragment(new ReditoFragment(), "REDITO");
        //adapter.addFragment(new ConfigFragment(),"CONFIGURACIÓN");
        //viewPager.setAdapter(adapter);
    }
}
