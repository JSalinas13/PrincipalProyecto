package com.example.principalproyecto.models;

public class ProductosDAO {
    private int idProducto;
    private String nomProducto;
    private int idCategroia;
    private int stockProducto;
    private float precioProducto;
    private float costoProducto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public int getIdCategroia() {
        return idCategroia;
    }

    public void setIdCategroia(int idCategroia) {
        this.idCategroia = idCategroia;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public float getCostoProducto() {
        return costoProducto;
    }

    public void setCostoProducto(float costoProducto) {
        this.costoProducto = costoProducto;
    }

    //CRUD
    // C --> CREATE
    // R --> READ
    // U --> UPDATE
    // D --> DELETE
    public void INSERT(){

    }

    public void UPDATE(){

    }

    public void DELETE(){

    }

    //Recupera todos los registros de la tabla
    public void SELECTALL(){

    }

    //Recupera solo un registro de la tabla por id
    public void SELECTID(){

    }


}
