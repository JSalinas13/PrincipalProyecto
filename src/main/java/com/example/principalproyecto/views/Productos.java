package com.example.principalproyecto.views;

import com.example.principalproyecto.models.ProductosDAO;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Productos extends Stage {
    private VBox vBox;
    private TableView<ProductosDAO> tbvProductos;
    private Button btnNuevo;
    private Scene escena;


    public Productos(){
        CrearUI();
        this.setTitle("Administrar productos");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvProductos = new TableView<>();
        btnNuevo = new Button("Nuevo producto");
        btnNuevo.setOnAction(event -> {});
        vBox = new VBox();
        vBox.getChildren().addAll(tbvProductos,btnNuevo);
        escena = new Scene(vBox,300,250);
    }


}
