package com.example.mauro.amplis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorEquipos extends BaseAdapter {

    Context contexto;
    List<Equipos> items;
    ViewHolder holder;

    ////Objeto para reciclar los layout de la lista y los items
    static class ViewHolder {
        TextView marca;
        TextView modelo;
        ImageView logo;
    }


    public AdaptadorEquipos(Context contexto, List<Equipos> items) {
        this.contexto = contexto;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();    //Retorna la cantidad de elementos de la lista
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //Retorna el item de la posicion
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId(); //Retorna el campo id del item buscado
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            v = inflater.inflate(R.layout.listitem_equipos,null);

            holder = new ViewHolder();
            holder.modelo = (TextView)v.findViewById(R.id.LblModelo);
            holder.marca = (TextView)v.findViewById(R.id.LblMarca);
            holder.logo = (ImageView)v.findViewById(R.id.LblLogo);

            v.setTag(holder);

        }
        else {
            holder = (ViewHolder)v.getTag();
        }

        holder.marca.setText(items.get(position).getMarca().toString());
        holder.modelo.setText(items.get(position).getModelo().toString());


        switch (items.get(position).getMarca().toString().toLowerCase()) {
            case "fender":
                holder.logo.setImageResource(R.mipmap.ic_fender);
                break;
            case "marshall":
                holder.logo.setImageResource(R.mipmap.ic_marshall);
                break;
            case "vox":
                holder.logo.setImageResource(R.mipmap.ic_vox);
                break;
            case "peavey":
                holder.logo.setImageResource(R.mipmap.ic_peavey);
                break;
            default: holder.logo.setImageResource(R.mipmap.ic_marshall);
        }

        return v;
    }

}
