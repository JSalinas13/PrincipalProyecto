package com.example.principalproyecto.views;

import com.example.principalproyecto.components.CellCustom;
import com.example.principalproyecto.models.ProductosDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Productos extends Stage {
    private VBox vBox;
    private TableView<ProductosDAO> tbvProductos;
    private Button btnNuevo;
    private Scene escena;
    private ProductosDAO objPDAAO;


    public Productos() {
        objPDAAO = new ProductosDAO();
        CrearUI();
        this.setTitle("Administrar productos");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvProductos = new TableView<>();
        btnNuevo = new Button("Nuevo producto");
        btnNuevo.setOnAction(event -> {
            new ProductoForm(tbvProductos);
        });
        vBox = new VBox();
        vBox.getChildren().addAll(tbvProductos, btnNuevo);
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        crearTabla();
        escena = new Scene(vBox, 700, 250);

    }

    private void crearTabla() {
        //ID
        TableColumn<ProductosDAO, Integer> tbcIdProducto = new TableColumn<ProductosDAO, Integer>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<ProductosDAO, Integer>("idProductos"));

        //Nombre
        TableColumn<ProductosDAO, String> tbcNomProducto = new TableColumn<ProductosDAO, String>("Nombre");
        tbcNomProducto.setCellValueFactory(new PropertyValueFactory<ProductosDAO, String>("nomProducto"));


        TableColumn<ProductosDAO, String> tbcEditar = new TableColumn<>("Accion");
        tbcEditar.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
            @Override
            public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> productosDAOStringTableColumn) {
                return new CellCustom(1);
            }
        });

        TableColumn<ProductosDAO, String> tbcBorrar = new TableColumn<>("Accion");
        tbcBorrar.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
            @Override
            public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> productosDAOStringTableColumn) {
                return new CellCustom(2);
            }
        });
        //
        tbvProductos.getColumns().addAll(tbcIdProducto, tbcNomProducto, tbcEditar, tbcBorrar);
        tbvProductos.setItems(objPDAAO.SELECTALL());
    }


}
