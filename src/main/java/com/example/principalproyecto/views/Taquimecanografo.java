package com.example.principalproyecto.views;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent> {

    private Scene escena;
    private VBox vBox, vTeclado;
    private HBox hFuncion, hNum, hTab, hMayus, hShif, hSpac;
    private TextArea txtFuente;
    private TextArea txtEscritura;
    private ToolBar tolOpciones;
    private Button btnAbrir;
    private Image imgOpcion;
    private ImageView imgvOpcion;
    private File file, archivo;
    private FileChooser flcArchivo;
    private Button[] btnFunc = new Button[13];
    private String[] arSimFun = {"ESC", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12"};
    private Button[] btnNum = new Button[14];
    private String[] arSimNum = {"|", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "'", "¿", "←"};
    private Button[] btnTab = new Button[14];
    private String[] arSimTab = {"↹", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "´", "+", "}"};
    private Button[] btnMayus = new Button[13];
    private String[] arSimMayus = {"BLOQ MAYUS", "A", "S", "D", "F", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12"};
    private Button[] btnShif = new Button[13];
    private String[] arSimShif = {"⇧", "< >", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "-", "⇧"};
    private Button[] btnSpac = new Button[9];
    private String[] arSimSpac = {"CTRL", "FN", "⊞", "ALT", "-----", "-----", "ALT GR", "FN", "CTRL"};

    private boolean ban = false;  //Detecta cuando presiono o suelto una tecla

    public Taquimecanografo() {
        CrearUI();
        this.setTitle("Taquimecanografo");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        tolOpciones = new ToolBar();
        txtFuente = new TextArea();
        txtFuente.setEditable(false);
        txtEscritura = new TextArea();
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.setOnKeyReleased(this);

        file = new File("src/main/java/com/example/principalproyecto/images/OpenFile.png");
        imgOpcion = new Image(file.toURI().toString());
        imgvOpcion = new ImageView(imgOpcion);
        imgvOpcion.setFitHeight(25);
        imgvOpcion.setFitWidth(25);
        btnAbrir = new Button();
        btnAbrir.setOnAction(actionEvent -> tlbOpciones(1));
        btnAbrir.setGraphic(imgvOpcion);
        btnAbrir.setId("btn_menu");
        tolOpciones.getItems().addAll(btnAbrir);


        hFuncion = new HBox();
        hNum = new HBox();
        hTab = new HBox();
        hMayus = new HBox();
        hShif = new HBox();
        hSpac = new HBox();

        hFuncion.setSpacing(10d);
        setButtons(btnFunc, hFuncion, arSimFun);
        hNum.setSpacing(10d);
        setButtons(btnNum, hNum, arSimNum);
        hTab.setSpacing(10d);
        setButtons(btnTab, hTab, arSimTab);
        hMayus.setSpacing(10d);
        setButtons(btnMayus, hMayus, arSimMayus);
        hShif.setSpacing(10d);
        setButtons(btnShif, hShif, arSimShif);
        hSpac.setSpacing(10d);
        setButtons(btnSpac, hSpac, arSimSpac);


        vTeclado = new VBox();
        vTeclado.getChildren().addAll(hFuncion, hNum, hTab, hMayus, hShif, hSpac);

        vBox.getChildren().addAll(tolOpciones, txtFuente, txtEscritura, vTeclado);
        escena = new Scene(vBox, 600, 300);
        file = new File("src/main/java/com/example/principalproyecto/css/style.css");
        escena.getStylesheets().add(file.toURI().toString());

    }

    private void setButtons(Button arBotnes[], HBox hBox, String arSimbolos[]) {
        for (int i = 0; i < arBotnes.length; i++) {
            arBotnes[i] = new Button(arSimbolos[i]);
            hBox.getChildren().addAll(arBotnes[i]);
        }
    }

    private void tlbOpciones(int opcion) {
        switch (opcion) {
            case 1:
                flcArchivo = new FileChooser();
                flcArchivo.setTitle("Buscar archivo..........");
                flcArchivo.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("TXT", "*.txt"));

                archivo = flcArchivo.showOpenDialog(this);
                break;


        }
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode().toString()) {
            case "↹":
                if (ban == false) {
                    btnTab[0].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[0].setStyle("-fx-background-color: gray");
                }
                break;
            case "Q":
                if (ban == false) {
                    btnTab[1].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[1].setStyle("-fx-background-color: gray");
                }
                break;
            case "W":
                if (ban == false) {
                    btnTab[2].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[2].setStyle("-fx-background-color: gray");
                }
                break;
            case "E":
                if (ban == false) {
                    btnTab[3].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[3].setStyle("-fx-background-color: gray");
                }
                break;
            case "R":
                if (ban == false) {
                    btnTab[4].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[4].setStyle("-fx-background-color: gray");
                }
                break;
            case "T":
                if (ban == false) {
                    btnTab[5].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[5].setStyle("-fx-background-color: gray");
                }
                break;
            case "Y":
                if (ban == false) {
                    btnTab[6].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[6].setStyle("-fx-background-color: gray");
                }
                break;
            case "U":
                if (ban == false) {
                    btnTab[7].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[7].setStyle("-fx-background-color: gray");
                }
                break;
            case "I":
                if (ban == false) {
                    btnTab[8].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[8].setStyle("-fx-background-color: gray");
                }
                break;
            case "O":
                if (ban == false) {
                    btnTab[9].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[9].setStyle("-fx-background-color: gray");
                }
                break;
            case "´":
                if (ban == false) {
                    btnTab[10].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[10].setStyle("-fx-background-color: gray");
                }
                break;
            case "+":
                if (ban == false) {
                    btnTab[11].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[11].setStyle("-fx-background-color: gray");
                }
                break;
            case "}":
                if (ban == false) {
                    btnTab[12].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[12].setStyle("-fx-background-color: gray");
                }
                break;
        }
        ban = !ban;
    }

}

