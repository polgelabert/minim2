package com.dsa.pol.minim2.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.dsa.pol.minim2.R;
import com.dsa.pol.minim2.Model.Producto;
import com.squareup.picasso.Picasso;


public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ProductoViewHolder> {

    private List<Producto> listaProducto;
    private int rowLayout;
    private Context context;
    //public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    public AdapterProducto(List<Producto> listaProducto, int rowLayout, Context context) {
        this.listaProducto = listaProducto;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        LinearLayout productoLayout;
        TextView nombre;
        TextView coste;
        //ImageView imagen;
        TextView stock;
        TextView ventas;

        public ProductoViewHolder(View v) {
            super(v);
            productoLayout = (LinearLayout) v.findViewById(R.id.producto_layoutPol);
            //movieImage = (ImageView) v.findViewById(R.id.movie_imagePol);
            nombre = (TextView) v.findViewById(R.id.nombreProducto);
            coste = (TextView) v.findViewById(R.id.costeProducto);
            stock = (TextView) v.findViewById(R.id.stockProducto);
            ventas = (TextView) v.findViewById(R.id.ventasProducto);
        }
    }


    @Override
    public AdapterProducto.ProductoViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProductoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, final int position) {
        /*String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);*/
        holder.nombre.setText(listaProducto.get(position).getNombreProducto());
        holder.coste.setText(listaProducto.get(position).getCosteProducto().toString());
        holder.stock.setText(listaProducto.get(position).getStock().toString());
        holder.ventas.setText(listaProducto.get(position).getVentas().toString());
    }

    @Override
    public int getItemCount() {
        return listaProducto.size();
    }
}