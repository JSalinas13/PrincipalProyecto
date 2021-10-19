package com.example.principalproyecto;

import com.example.principalproyecto.models.Conexion;
import com.example.principalproyecto.views.Loteria;
import com.example.principalproyecto.views.Productos;
import com.example.principalproyecto.views.Taquimecanografo;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application implements EventHandler<WindowEvent> {

    private BorderPane bdPrincipal;
    private MenuBar mbOpciones;
    private Menu mOpcion1, mOpcion2, mSalir;
    private MenuItem mtLoteria, mtSalir, mTaqui, mtTienda;
    private Scene scene;
    private Conexion conexion;

    @Override
    public void start(Stage stage) throws IOException {
        CrearUI();

        scene = new Scene(bdPrincipal);
        stage.setScene(scene);
        stage.addEventHandler(WindowEvent.WINDOW_SHOWING, this);
        stage.setMaximized(true);
        stage.setTitle("Hello!");
        stage.show();
    }

    private void CrearUI() {
        conexion = new Conexion();
        bdPrincipal = new BorderPane();
        mbOpciones = new MenuBar();
        mOpcion1 = new Menu("Competencia 1");
        mOpcion2 = new Menu("Competencia 2");
        mSalir = new Menu("Salir");

        bdPrincipal.setTop(mbOpciones);

        //Agregar menus a MenuBar
        mbOpciones.getMenus().addAll(mOpcion1, mOpcion2, mSalir);

        //Instanciamos los MenuItems
        mtLoteria = new MenuItem("Loteria");
        mTaqui = new MenuItem("Taquimecanografo");
        mtTienda = new MenuItem("Tienda");
        mtLoteria.setOnAction(actionEvent -> MenuOpciones(1));
        mTaqui.setOnAction(actionEvent -> MenuOpciones(2));
        mtTienda.setOnAction(actionEvent -> MenuOpciones(3));
//        mtLoteria.setOnAction(actionEvent -> {
//            new Loteria();
//        });
        mOpcion1.getItems().addAll(mtLoteria, mTaqui, mtTienda);


        mtSalir = new MenuItem("Hasta luego...");
        mtSalir.setOnAction(actionEvent -> MenuOpciones(20));
        mSalir.getItems().addAll(mtSalir);
    }

    private void MenuOpciones(int i) {
        switch (i) {
            case 1:
                new Loteria();
                break;
            case 2:
                new Taquimecanografo();
                break;
            case 3:
                new Productos();
                break;
            case 20:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void handle(WindowEvent windowEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion del sistema");
        alert.setHeaderText("Salinas León Jesus Trinidad");
        alert.setContentText("Bienvenido al sistema del curso de programción :)");
        alert.showAndWait();

        try {
            Conexion.getConexion();
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            Alert alertErrorBD = new Alert(Alert.AlertType.ERROR);
            alertErrorBD.setTitle("Informacion del sistema");
            alertErrorBD.setHeaderText("Informacion sobre la conexion de la Base de Datos");
            alertErrorBD.setContentText(e.getMessage());
            alertErrorBD.showAndWait();
            System.exit(0);
        }
    }
}