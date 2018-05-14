package com.example.mauro.amplis;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorEquipos extends ArrayAdapter<Equipos> {

    //Fragment contexto;
    ////Objeto para reciclar los layout de la lista y los items
    static class ViewHolder {
        TextView marca;
        TextView modelo;
    }

    public Equipos[] datos;

    //Construcor del adaptador. 1ro el contexto de la activity donde se crea, 2do ID del layout que definimos XML,
    // 3ro el array de datos
    public AdaptadorEquipos(Context context, Equipos[] amplisAux) {
        super(context, R.layout.listitem_equipos, amplisAux);
        this.datos=amplisAux;
    }

/*

    public AdaptadorEquipos(Fragment context, Equipos[] datos) {
        super(context.getActivity(), R.layout.listitem_equipos, datos);
        this.datos = datos;
        this.contexto = context.getParentFragment();
    }
*/


    public View getView(int position, View convertView, ViewGroup parent)
    {
        View item = convertView;
        ViewHolder holder;

        if(item == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.listitem_equipos, null);

            holder = new ViewHolder();
            holder.modelo = (TextView)item.findViewById(R.id.LblModelo);
            holder.marca = (TextView)item.findViewById(R.id.LblMarca);

            item.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)item.getTag();
        }

        holder.marca.setText(datos[position].getMarca());
        holder.modelo.setText(datos[position].getModelo());

        return(item);
    }
}
