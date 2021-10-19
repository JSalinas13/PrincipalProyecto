package com.example.principalproyecto.components;

import com.example.principalproyecto.models.ProductosDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class CellCustom extends TableCell<ProductosDAO, String> {
    private Button btnCelada;
    private int opcion;
    private ProductosDAO objPDAO;

    public CellCustom(int opcion) {
        this.opcion = opcion;
        if (opcion == 1) {
            btnCelada = new Button("Editar");
        } else {
            btnCelada = new Button("Borrar");
            btnCelada.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alerta del Sistema");
                alert.setHeaderText("Confirmacion de la accion");
                alert.setContentText("¿Realmente deseas eliminar este producto?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    objPDAO = CellCustom.this.getTableView().getItems().get(CellCustom.this.getIndex());
                    objPDAO.DELETE();
                    CellCustom.this.getTableView().setItems(objPDAO.SELECTALL());
                    CellCustom.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
    }
}
