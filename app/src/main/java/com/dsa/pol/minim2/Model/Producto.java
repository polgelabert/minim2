package com.dsa.pol.minim2.Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto implements Serializable
{

    @SerializedName("nombreProducto")
    @Expose
    private String nombreProducto;
    @SerializedName("costeProducto")
    @Expose
    private Integer costeProducto;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("ventas")
    @Expose
    private Integer ventas;
    private final static long serialVersionUID = -8217136443784084419L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Producto() {
    }

    /**
     *
     * @param stock
     * @param ventas
     * @param costeProducto
     * @param nombreProducto
     */
    public Producto(String nombreProducto, Integer costeProducto, Integer stock, Integer ventas) {
        super();
        this.nombreProducto = nombreProducto;
        this.costeProducto = costeProducto;
        this.stock = stock;
        this.ventas = ventas;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Producto withNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
        return this;
    }

    public Integer getCosteProducto() {
        return costeProducto;
    }

    public void setCosteProducto(Integer costeProducto) {
        this.costeProducto = costeProducto;
    }

    public Producto withCosteProducto(Integer costeProducto) {
        this.costeProducto = costeProducto;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Producto withStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }

    public Producto withVentas(Integer ventas) {
        this.ventas = ventas;
        return this;
    }

}