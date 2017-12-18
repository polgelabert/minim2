package com.dsa.pol.minim2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Pol on 18/12/2017.
 */

public class ProductoResponse {

    @SerializedName("results")
    private ArrayList<Producto> results;




    public ArrayList<Producto> getResults() {
        return results;
    }

    public void setResults(ArrayList<Producto> results) {
        this.results = results;
    }
}
