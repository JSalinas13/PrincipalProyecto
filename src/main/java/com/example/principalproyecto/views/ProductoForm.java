package com.example.principalproyecto.views;

import com.example.principalproyecto.models.ProductosDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ProductoForm extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtNombre, txtCategorias, txtStock, txtPrecio, txtCosto;
    private Button btnGuardar;
    private ProductosDAO objPDAO;
    private TableView<ProductosDAO> tbvProductos;

    public ProductoForm(TableView<ProductosDAO> tbvProductos) {
        this.tbvProductos = tbvProductos;
        CrearUI();
        this.setTitle("Gestion de producto");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        objPDAO = new ProductosDAO();
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del producto");
        txtCategorias = new TextField();
        txtCategorias.setPromptText("Categoria del producto");
        txtStock = new TextField();
        txtStock.setPromptText("Numero de existencia");
        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio sugerido");
        txtCosto = new TextField();
        txtCosto.setPromptText("Costo del producto");
        btnGuardar = new Button("Guardar");
        //Se dan de alta los productos
        btnGuardar.setOnAction(event -> {
            objPDAO.setNomProducto(txtNombre.getText());
            objPDAO.setIdCategroia(Integer.parseInt(txtCategorias.getText()));
            objPDAO.setStockProducto(Integer.parseInt(txtStock.getText()));
            objPDAO.setPrecioProducto(Float.parseFloat(txtPrecio.getText()));
            objPDAO.setCostoProducto(Float.parseFloat(txtCosto.getText()));
            objPDAO.INSERT();
            tbvProductos.setItems(objPDAO.SELECTALL());
            tbvProductos.refresh();
            this.close();
        });
        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(txtNombre, txtCategorias, txtStock, txtPrecio, txtCosto, btnGuardar);
        escena = new Scene(vBox, 300, 250);
    }
}
