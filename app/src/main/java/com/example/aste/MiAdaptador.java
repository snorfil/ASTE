package com.example.aste;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//Clase para rellenar el RecyclerView
public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {
    private LayoutInflater inflador;
    private List<String> lista;

    //Escuchador para ver qué elemento del RecyclerView se ha pulsado
    protected View.OnClickListener onClickListener;

    //Se inicializa el conjunto de datos a mostrar (vector puntuaciones).
    //El inflador permitirá crear una vista a partir de su XML.
    public MiAdaptador (Context context, List<String> lista){
        this.lista = lista;
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Devuelve una vista de un elemento sin personalizar.
    @Override public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        //Se crea una vista a partir del layout XML definido en elemento_lista
        View v = inflador.inflate(R.layout.elemento_lista, parent, false);

        //Se añade el escuchador onClickListener para saber qué elemento del RecyclerView se pulsta
        v.setOnClickListener(onClickListener);

        return new ViewHolder(v);
    }

    //Personaliza un elemento de tipo ViewHolder según su posición
    @Override public void onBindViewHolder (ViewHolder holder, int i) {
        holder.nombre.setText(lista.get(i).substring(0, lista.get(i).indexOf((" "))));
        holder.puntuacion.setText(lista.get(i).substring(lista.get(i).indexOf((" "))));
        switch (Math.round((float)Math.random()*3)){
            case 0:
                holder.icon.setImageResource(R.drawable.asteroide1);
                break;
            case 1:
                holder.icon.setImageResource(R.drawable.asteroide2);
                break;
            default:
                holder.icon.setImageResource(R.drawable.asteroide3);
                break;
        }
    }

    //Indicar el número de elementos a visualizar
    @Override
    public int getItemCount (){
        return lista.size();
    }

    //Clase ViewHolder que contendrá las vistas que queremos modificar de un elemento:
    //2 TextView con el título y subtítulo y 1 ImageView con la imagen.
    //Se utiliza esta clase para evitar tener que crear las vistas de cada elemento desde cero.
    //Gastará el mismo ViewHolder para todos los elementos y lo personalizaremos según
    //la posición (reciclamos el ViewHolder y hace que funcione más rápido).
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, puntuacion;
        public ImageView icon;

        ViewHolder (View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.nombre);
            puntuacion = (TextView)itemView.findViewById(R.id.puntuacion);
            icon = (ImageView)itemView.findViewById(R.id.icono);
        }
    }

    //setter para modificar el objeto onClickListener
    public void setOnClickListener (View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
