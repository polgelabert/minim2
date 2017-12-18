package com.dsa.pol.minim2.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.dsa.pol.minim2.Adapter.AdapterProducto;
import com.dsa.pol.minim2.Model.Producto;
import com.dsa.pol.minim2.Model.ProductoResponse;
import com.dsa.pol.minim2.R;
import com.dsa.pol.minim2.Rest.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DisplayProdOrdAscPrecio extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://localhost:8080/minim1/json/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_productos);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //String titol = (String) getIntent().getExtras().get("title");
        //Log.d(TAG, titol);

        GetApiProdOrdAscCoste();

    }

    // This method create an instance of Retrofit
    // set the base url
    public void GetApiProdOrdAscCoste(){


        final ProgressDialog progDialog = new ProgressDialog(DisplayProdOrdAscPrecio.this);
        progDialog.setIndeterminate(true);              // Tipus de progressbar
        progDialog.setTitle("Productos de la despensa Ordenados por precio Ascendente");
        progDialog.setMessage("Consultant la cerca...");
        progDialog.show();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ProductoApiService productoApiService = retrofit.create(ProductoApiService.class);


        Call<ProductoResponse> call = productoApiService.getListaProdOrdAscVentas();
        call.enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
                List<Producto> ListaProducto = new ArrayList<>();
                ArrayList<Producto> arrayProducto = response.body().getResults();
                ListaProducto.addAll(arrayProducto);



                recyclerView.setAdapter(new AdapterProducto(ListaProducto, R.layout.producto, getApplicationContext()));

                Log.d(TAG, "Number of products received: " + ListaProducto.size());
                stopProgDialog(progDialog);
            }

            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

    private void stopProgDialog(final ProgressDialog progDialog){
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                progDialog.dismiss();       // Tanca progressbar
                t.cancel();                 // Cancela el timer
            }
        }, 2000);                    // Delay de 2s per mostrar la informació
    }



}