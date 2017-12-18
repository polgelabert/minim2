package com.dsa.pol.minim2.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dsa.pol.minim2.Adapter.AdapterProducto;
import com.dsa.pol.minim2.Model.Producto;
import com.dsa.pol.minim2.Model.ProductoResponse;
import com.dsa.pol.minim2.R;
import com.dsa.pol.minim2.Rest.ProductoApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://localhost:8080/minim1/json/";
    private static Retrofit retrofit = null;

    Button cerca;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText user = (EditText) findViewById(R.id.user);
        final EditText password = (EditText) findViewById(R.id.passsword);
        cerca = (Button) findViewById(R.id.login);


        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    if (TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Vaja! ): Sembla que no he entès aquest usuari o contrasenya...", Toast.LENGTH_LONG).show();
                        return;
                    }



                    final ProgressDialog progDialog = new ProgressDialog(MainActivity.this);
                    progDialog.setIndeterminate(true);              // Tipus de progressbar
                    progDialog.setTitle("LOGIN");
                    progDialog.setMessage("Consultant l'usuaria a la base dades...");
                    progDialog.show();

                    if (retrofit == null) {
                        retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                    }

                    ProductoApiService productoApiService = retrofit.create(ProductoApiService.class);


                    Call<Boolean> call = productoApiService.getlogin();
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body()){
                                stopProgDialog(progDialog);
                            }

                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable throwable) {
                            Log.e(TAG, throwable.toString());
                        }
                    });




                    intent = new Intent(MainActivity.this, DisplayProdOrdAscPrecio.class);
                    //intent.putExtra("title", user.getText().toString());
                    startActivity(intent);

                } catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error desconegut", Toast.LENGTH_LONG).show();
                }

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
