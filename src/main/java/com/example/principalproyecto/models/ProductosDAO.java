package com.example.principalproyecto.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProductosDAO {
    private int idProducto;
    public String nomProducto;
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
    public void INSERT() {
        try {
            String query = "INSERT INTO tblproductos (nomProducto,idCategoria,stockProducto,precioProducto,costoProducto) " +
                    "VALUES ('" + nomProducto + "'," + idCategroia + "," + stockProducto + "," + precioProducto + "," + costoProducto + ")";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();//DEBUG MOODE
        }
    }

    public void UPDATE() {
        try {
            String query = "UPDATE tblproductos SET no momProducto= '" + nomProducto + "', idCategoria= " + idProducto +
                    ", stockProducto= " + stockProducto + ", precioProducto= " + precioProducto + ", costoProducto= " + costoProducto +
                    " WHERE idProducto = " + idProducto;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();//DEBUG MOODE
        }
    }

    public void DELETE() {
        try {
            String query = "DELETE FROM tblproductos WHERE idProductos = " + idProducto;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();//DEBUG MOODE
        }
    }

    //Recupera todos los registros de la tabla
    public ObservableList<ProductosDAO> SELECTALL() {
        ObservableList<ProductosDAO> listP = FXCollections.observableArrayList();
        try {
            ProductosDAO objP;
            String query = "SELECT * FROM tblproductos";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                objP = new ProductosDAO();
                objP.setIdProducto(res.getInt("idProductos"));
                objP.setNomProducto(res.getString("nomProducto"));
                objP.setIdCategroia(res.getInt("idCategoria"));
                objP.setStockProducto(res.getInt("stockProducto"));
                objP.setPrecioProducto(res.getFloat("precioProducto"));
                objP.setCostoProducto(res.getFloat("costoProducto"));
                listP.add(objP);
            }
        } catch (Exception e) {
            e.printStackTrace();//DEBUG MODE
        }
        return listP;
    }

    //Recupera solo un registro de la tabla por id
    public ProductosDAO SELECTID() {
        ProductosDAO objP = null;
        try {
            String query = "SELECT * FROM tblproductos WHERE idProducto =" + idProducto;
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                objP = new ProductosDAO();
                objP.setIdProducto(res.getInt("idProductos"));
                objP.setNomProducto(res.getString("nomProducto"));
                objP.setIdCategroia(res.getInt("idCategoria"));
                objP.setStockProducto(res.getInt("stockProducto"));
                objP.setPrecioProducto(res.getFloat("precioProducto"));
                objP.setCostoProducto(res.getFloat("costoProducto"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objP;
    }


}
