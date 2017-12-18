package com.dsa.pol.minim2.Rest;

import com.dsa.pol.minim2.Model.Producto;
import com.dsa.pol.minim2.Model.ProductoResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;


public interface ProductoApiService {

    //@GET("movie/{id}")
    //Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("listaProdOrdDescVentas")
    Call<ProductoResponse> getListaProdOrdAscVentas();

    @GET("login")
    Call<Boolean> getlogin();


}