package com.example.principalproyecto.views;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent> {

    private Scene escena;
    private VBox vBox, vTeclado;
    private HBox hFuncion, hNum, hTab, hMayus, hShif, hSpac;
    private TextArea txtFuente;
    private TextArea txtEscritura;
    private ToolBar tolOpciones;
    private Button btnAbrir, btnFinalizar;
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
    private String[] arSimMayus = {"BLOQ MAYUS", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ", "{", "↲"};
    private Button[] btnShif = new Button[13];
    private String[] arSimShif = {"⇧", "< >", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "-", "⇧"};
    private Button[] btnSpac = new Button[6];
    private String[] arSimSpac = {"CTRL", "⊞", "ALT", "--------------------------------", "ALT", "CTRL"};
    private int contErrores, contFaltante, contSobrante, contE;
    private RandomAccessFile entrada;
    private String texto = "", temp = "", escrito = "", tiempo;
    private boolean banderaArchivo = false;
    private Label lblErrores, lblTiempo;
    private int segundos, minutos, horas, palabrasEscritas = 1;
    Timer timer;

    private boolean ban = false;  //Detecta cuando presiono o suelto una tecla

    public Taquimecanografo() {
        CrearUI();
        this.setTitle("Taquimecanografo");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (segundos == 60) {
                            segundos = 0;
                            ++minutos;
                        } else if (minutos == 60) {
                            minutos = 0;
                            ++horas;
                        } else {
                            ++segundos;
                        }
                        tiempo = "Tiempo: " + horas + ":" + minutos + ":" + segundos;
                        lblTiempo.setText(tiempo);
                    }
                });
            }
        });

        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        tolOpciones = new ToolBar();
        txtFuente = new TextArea();
        txtFuente.setEditable(false);
        txtEscritura = new TextArea();
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.setOnKeyReleased(this);
        txtEscritura.setDisable(true);
        lblTiempo = new Label();
        lblErrores = new Label();
        lblErrores.setText("Errores cometidos: " + contErrores);
        tiempo = "Tiempo: " + horas + ":" + minutos + ":" + segundos;
        lblTiempo.setText(tiempo);

        file = new File("src/main/java/com/example/principalproyecto/images/OpenFile.png");
        imgOpcion = new Image(file.toURI().toString());
        imgvOpcion = new ImageView(imgOpcion);
        imgvOpcion.setFitHeight(25);
        imgvOpcion.setFitWidth(25);
        btnAbrir = new Button();
        btnAbrir.setOnAction(actionEvent -> tlbOpciones(1));
        btnAbrir.setGraphic(imgvOpcion);
        btnAbrir.setId("btn_menu");
        btnFinalizar = new Button("Comprobar escritura...");
        btnFinalizar.setId("btn_Finalizar");
        tolOpciones.getItems().addAll(btnAbrir, lblErrores, btnFinalizar, lblTiempo);

        //Boton de comprobacion
        btnFinalizar.setOnAction(event -> {
            if (!txtEscritura.getText().equals("") || !txtFuente.getText().equals("")) {
                escrito = txtEscritura.getText();
                System.out.println("Texto lenght: " + texto.length());
                System.out.println("Escrito lenrht: " + escrito.length());
                for (int i = 0; i < texto.length(); i++) {
                    System.out.println("Texto: " + texto.charAt(i));
                }
                for (int i = 0; i < escrito.length(); i++) {
                    System.out.println("Escrito: " + escrito.charAt(i));
                }

                if (!escrito.equals("")) {
                    if (texto.length() == escrito.length()) {
                        for (int i = 0; i < texto.length(); i++) {
                            if (!String.valueOf(texto.charAt(i)).equals(String.valueOf(escrito.charAt(i)))) {
                                contE += 1;
                            }
                        }

                        if (contE == 0) {
                            timer.stop();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Taquimecanografia");
                            alert.setHeaderText(null);
                            alert.setContentText("El texto que escribiste coinciden, felicidades..." +
                                    "\nErrores cometidos: " + contErrores +
                                    "\nTiempo: " + tiempo +
                                    "\nPalabras escritas: " + palabrasEscritas(escrito));
                            alert.showAndWait();
                            txtFuente.setText("");
                            txtEscritura.setText("");
                            segundos = 0;
                            minutos = 0;
                            horas = 0;
                            tiempo = "Tiempo: " + horas + ":" + minutos + ":" + segundos;
                            lblTiempo.setText(tiempo);
                            contErrores = 0;
                            lblErrores.setText("Errores cometidos: " + contErrores);
                            txtEscritura.setDisable(true);
                        }

                        contErrores += contE;
                        contE = 0;
                    } else if (texto.length() > escrito.length()) {
                        for (int i = 0; i < escrito.length(); i++) {
                            if (!String.valueOf(escrito.charAt(i)).equals(String.valueOf(texto.charAt(i)))) {
                                contErrores += 1;
                            }
                        }
                        contFaltante = (texto.length() - 1) - (escrito.length() - 1);
                        contErrores += contFaltante;
                        contFaltante = 0;
                    } else if (escrito.length() > texto.length()) {
                        for (int i = 0; i < texto.length(); i++) {
                            if (!String.valueOf(texto.charAt(i)).equals(String.valueOf(escrito.charAt(i)))) {
                                contErrores += 1;
                            }
                        }
                        contSobrante = (escrito.length() - 1) - (texto.length() - 1);
                        contErrores += contSobrante;
                        contSobrante = 0;
                    }
                } else {
                    System.out.println("Vacio");
                }
            }
            lblErrores.setText("Errores cometidos: " + contErrores);
            System.out.println("Errores: " + contErrores);
        });


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


        /*Asignacion de los id para cambiar tamaño*/
        //btnFun
        for (int i = 0; i < 13; i++) {
            btnFunc[i].setId("btn_Fun");
        }

        //btnNum
        for (int i = 0; i < 13; i++) {
            btnNum[i].setId("btn_TamNormal");
        }
        btnNum[13].setId("btn_Largos");

        //btnTab
        for (int i = 1; i < 13; i++) {
            btnTab[i].setId("btn_TamNormal");
        }
        btnTab[0].setId("btn_Medianos");
        btnTab[13].setId("btn_Medianos");

        //btnMayus
        for (int i = 1; i < 12; i++) {
            btnMayus[i].setId("btn_TamNormal");
        }
        btnMayus[0].setId("btn_Largos");
        btnMayus[12].setId("btn_Largos");

        //btnShif
        for (int i = 1; i < 12; i++) {
            btnShif[i].setId("btn_TamNormal");
        }
        btnShif[0].setId("btn_Medianos");
        btnShif[12].setId("btn_Largos");

        //btnSpace
        for (int i = 0; i < 3; i++) {
            btnSpac[i].setId("btn_TamNormal");
        }
        for (int i = 4; i < 2; i++) {
            btnSpac[i].setId("btn_TamNormal");
        }

        vTeclado = new VBox();
        hFuncion.setAlignment(Pos.CENTER);
        hNum.setAlignment(Pos.CENTER);
        hTab.setAlignment(Pos.CENTER);
        hMayus.setAlignment(Pos.CENTER);
        hShif.setAlignment(Pos.CENTER);
        hSpac.setAlignment(Pos.CENTER);
        vTeclado.getChildren().addAll(hFuncion, hNum, hTab, hMayus, hShif, hSpac);
        vTeclado.setPadding(new Insets(40));
        vTeclado.setStyle("-fx-background-color: #FFF;");
        vBox.getChildren().addAll(tolOpciones, txtFuente, txtEscritura, vTeclado);
        vBox.setStyle("-fx-background-color: #000;");
        escena = new Scene(vBox, 900, 700);


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
                try {
                    txtEscritura.setText("");
                    timer.stop();
                    texto = "";
                    escrito = "";
                    temp = "";
                    horas = 0;
                    minutos = 0;
                    segundos = 0;
                    tiempo = "Tiempo: " + horas + ":" + minutos + ":" + segundos;
                    lblTiempo.setText(tiempo);
                    contErrores = 0;
                    lblErrores.setText("Errores cometidos: " + contErrores);
                    txtFuente.setText("");
                    flcArchivo = new FileChooser();
                    flcArchivo.setTitle("Buscar archivo..........");
                    flcArchivo.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("TXT", "*.txt"));

                    archivo = flcArchivo.showOpenDialog(this);
                    entrada = new RandomAccessFile(archivo, "rw");
                    entrada.seek(0);
                    while (entrada.getFilePointer() < entrada.length()) {
                        System.out.println(entrada.getFilePointer());
                        temp += entrada.readLine() + "\n";
                        entrada.seek(entrada.getFilePointer());
                    }
                    for (int i = 0; i < temp.length() - 1; i++) {
                        texto += temp.charAt(i);
                    }

                    if (!texto.equals("")) {
                        txtFuente.setText(texto);
                        timer.start();
                        txtEscritura.setDisable(false);
                    }

                } catch (Exception e) {
                    System.err.println("Error al generar RandomAccessFile");
                }
                break;
        }
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode().toString()) {
            //SimFun
            case "ESC":
            case "ESCAPE":
                if (ban == false) {
                    btnFunc[0].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[0].setStyle("-fx-background-color: black");
                }
                break;
            case "F1":
                if (ban == false) {
                    btnFunc[1].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[1].setStyle("-fx-background-color: black");
                }
                break;
            case "F2":
                if (ban == false) {
                    btnFunc[2].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[2].setStyle("-fx-background-color: black");
                }
                break;
            case "F3":
                if (ban == false) {
                    btnFunc[3].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[3].setStyle("-fx-background-color: black");
                }
                break;
            case "F4":
                if (ban == false) {
                    btnFunc[4].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[4].setStyle("-fx-background-color: black");
                }
                break;
            case "F5":
                if (ban == false) {
                    btnFunc[5].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[5].setStyle("-fx-background-color: black");
                }
                break;
            case "F6":
                if (ban == false) {
                    btnFunc[6].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[6].setStyle("-fx-background-color: black");
                }
                break;
            case "F7":
                if (ban == false) {
                    btnFunc[7].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[7].setStyle("-fx-background-color: black");
                }
                break;
            case "F8":
                if (ban == false) {
                    btnFunc[8].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[8].setStyle("-fx-background-color: black");
                }
                break;
            case "F9":
                if (ban == false) {
                    btnFunc[9].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[9].setStyle("-fx-background-color: black");
                }
                break;
            case "F10":
                if (ban == false) {
                    btnFunc[10].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[10].setStyle("-fx-background-color: black");
                }
                break;
            case "F11":
                if (ban == false) {
                    btnFunc[11].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[11].setStyle("-fx-background-color: black");
                }
                break;
            case "F12":
                if (ban == false) {
                    btnFunc[12].setStyle("-fx-background-color: blue");
                } else {
                    btnFunc[12].setStyle("-fx-background-color: black");
                }
                break;

            //SimNum
            case "|":
                //case "UNDEFINED":
                if (ban == false) {
                    btnNum[0].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[0].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT1":
            case "1":
                if (ban == false) {
                    btnNum[1].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[1].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT2":
            case "2":
                if (ban == false) {
                    btnNum[2].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[2].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT3":
            case "3":
                if (ban == false) {
                    btnNum[3].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[3].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT4":
            case "4":
                if (ban == false) {
                    btnNum[4].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[4].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT5":
            case "5":
                if (ban == false) {
                    btnNum[5].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[5].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT6":
            case "6":
                if (ban == false) {
                    btnNum[6].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[6].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT7":
            case "7":
                if (ban == false) {
                    btnNum[7].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[7].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT8":
            case "8":
                if (ban == false) {
                    btnNum[8].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[8].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT9":
            case "9":
                if (ban == false) {
                    btnNum[9].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[9].setStyle("-fx-background-color: black");
                }
                break;
            case "DIGIT0":
            case "0":
                if (ban == false) {
                    btnNum[10].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[10].setStyle("-fx-background-color: black");
                }
                break;
            case "QUOTE":
            case "'":
                if (ban == false) {
                    btnNum[11].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[11].setStyle("-fx-background-color: black");
                }
                break;
            case "UNDEFINED":
            case "¿":
                if (ban == false) {
                    btnNum[12].setStyle("-fx-background-color: blue");
                } else {
                    btnNum[12].setStyle("-fx-background-color: black");
                }
                break;
            case "BACK_SPACE":
            case "←":
                if (ban == false) {
                    btnNum[13].setStyle("-fx-background-color: blue");
                    contErrores += 1;
                    lblErrores.setText("Errores cometidos: " + contErrores);
                } else {
                    btnNum[13].setStyle("-fx-background-color: black");
                }
                break;

            //SimTab
            case "↹":
            case "TAB":
                if (ban == false) {
                    btnTab[0].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[0].setStyle("-fx-background-color: black");
                }
                break;
            case "Q":
                if (ban == false) {
                    btnTab[1].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[1].setStyle("-fx-background-color: black");
                }
                break;
            case "W":
                if (ban == false) {
                    btnTab[2].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[2].setStyle("-fx-background-color: black");
                }
                break;
            case "E":
                if (ban == false) {
                    btnTab[3].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[3].setStyle("-fx-background-color: black");
                }
                break;
            case "R":
                if (ban == false) {
                    btnTab[4].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[4].setStyle("-fx-background-color: black");
                }
                break;
            case "T":
                if (ban == false) {
                    btnTab[5].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[5].setStyle("-fx-background-color: black");
                }
                break;
            case "Y":
                if (ban == false) {
                    btnTab[6].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[6].setStyle("-fx-background-color: black");
                }
                break;
            case "U":
                if (ban == false) {
                    btnTab[7].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[7].setStyle("-fx-background-color: black");
                }
                break;
            case "I":
                if (ban == false) {
                    btnTab[8].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[8].setStyle("-fx-background-color: black");
                }
                break;
            case "O":
                if (ban == false) {
                    btnTab[9].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[9].setStyle("-fx-background-color: black");
                }
                break;
            case "P":
                if (ban == false) {
                    btnTab[10].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[10].setStyle("-fx-background-color: black");
                }
                break;
            case "´":
            case "DEAD_ACUTE":
                if (ban == false) {
                    btnTab[11].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[11].setStyle("-fx-background-color: black");
                }
                break;
            case "+":
            case "PLUS":
                if (ban == false) {
                    btnTab[12].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[12].setStyle("-fx-background-color: black");
                }
                break;
            case "}":
            case "BRACERIGHT":
                if (ban == false) {
                    btnTab[13].setStyle("-fx-background-color: blue");
                } else {
                    btnTab[13].setStyle("-fx-background-color: black");
                }
                break;

            //SimMayus
            case "BLOQ MAYUS":
            case "CAPS":
                if (ban == false) {
                    btnMayus[0].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[0].setStyle("-fx-background-color: black");
                }
                break;
            case "A":
                if (ban == false) {
                    btnMayus[1].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[1].setStyle("-fx-background-color: black");
                }
                break;
            case "S":
                if (ban == false) {
                    btnMayus[2].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[2].setStyle("-fx-background-color: black");
                }
                break;
            case "D":
                if (ban == false) {
                    btnMayus[3].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[3].setStyle("-fx-background-color: black");
                }
                break;
            case "F":
                if (ban == false) {
                    btnMayus[4].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[4].setStyle("-fx-background-color: black");
                }
                break;
            case "G":
                if (ban == false) {
                    btnMayus[5].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[5].setStyle("-fx-background-color: black");
                }
                break;
            case "H":
                if (ban == false) {
                    btnMayus[6].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[6].setStyle("-fx-background-color: black");
                }
                break;
            case "J":
                if (ban == false) {
                    btnMayus[7].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[7].setStyle("-fx-background-color: black");
                }
                break;
            case "K":
                if (ban == false) {
                    btnMayus[8].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[8].setStyle("-fx-background-color: black");
                }
                break;
            case "L":
                if (ban == false) {
                    btnMayus[9].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[9].setStyle("-fx-background-color: black");
                }
                break;
            case "Ñ":
                if (ban == false) {
                    btnMayus[10].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[10].setStyle("-fx-background-color: black");
                }
                break;
            case "{":
            case "BRACELEFT":
                if (ban == false) {
                    btnMayus[11].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[11].setStyle("-fx-background-color: black");
                }
                break;
            case "↲":
            case "ENTER":
                if (ban == false) {
                    btnMayus[12].setStyle("-fx-background-color: blue");
                } else {
                    btnMayus[12].setStyle("-fx-background-color: black");
                }
                break;

            //SimShif
            case "⇧":
            case "SHIFT":
                if (ban == false) {
                    btnShif[0].setStyle("-fx-background-color: blue");
                    btnShif[12].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[0].setStyle("-fx-background-color: black");
                    btnShif[12].setStyle("-fx-background-color: black");
                }
                break;

            case "< >":
            case "LESS":
                if (ban == false) {
                    btnShif[1].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[1].setStyle("-fx-background-color: black");
                }
                break;

            case "Z":
                if (ban == false) {
                    btnShif[2].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[2].setStyle("-fx-background-color: black");
                }
                break;
            case "X":
                if (ban == false) {
                    btnShif[3].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[3].setStyle("-fx-background-color: black");
                }
                break;
            case "C":
                if (ban == false) {
                    btnShif[4].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[4].setStyle("-fx-background-color: black");
                }
                break;
            case "V":
                if (ban == false) {
                    btnShif[5].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[5].setStyle("-fx-background-color: black");
                }
                break;
            case "B":
                if (ban == false) {
                    btnShif[6].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[6].setStyle("-fx-background-color: black");
                }
                break;
            case "N":
                if (ban == false) {
                    btnShif[7].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[7].setStyle("-fx-background-color: black");
                }
                break;
            case "M":
                if (ban == false) {
                    btnShif[8].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[8].setStyle("-fx-background-color: black");
                }
                break;
            case ",":
            case "COMMA":
                if (ban == false) {
                    btnShif[9].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[9].setStyle("-fx-background-color: black");
                }
                break;
            case ".":
            case "PERIOD":
                if (ban == false) {
                    btnShif[10].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[10].setStyle("-fx-background-color: black");
                }
                break;
            case "-":
            case "MINUS":
                if (ban == false) {
                    btnShif[11].setStyle("-fx-background-color: blue");
                } else {
                    btnShif[11].setStyle("-fx-background-color: black");
                }
                break;

            //SimSpac
            case "CTRL":
            case "CONTROL":
                if (ban == false) {
                    btnSpac[0].setStyle("-fx-background-color: blue");
                    btnSpac[5].setStyle("-fx-background-color: blue");
                } else {
                    btnSpac[0].setStyle("-fx-background-color: black");
                    btnSpac[5].setStyle("-fx-background-color: black");
                }
                break;
            case "⊞":
            case "WINDOWS":
                if (ban == false) {
                    btnSpac[1].setStyle("-fx-background-color: blue");
                } else {
                    btnSpac[1].setStyle("-fx-background-color: black");
                }
                break;
            case "ALT":
                if (ban == false) {
                    btnSpac[2].setStyle("-fx-background-color: blue");
                    btnSpac[4].setStyle("-fx-background-color: blue");
                } else {
                    btnSpac[2].setStyle("-fx-background-color: black");
                    btnSpac[4].setStyle("-fx-background-color: black");
                }
                break;
            case "--------------------------------":
            case "SPACE":
                if (ban == false) {
                    btnSpac[3].setStyle("-fx-background-color: blue");
                } else {
                    btnSpac[3].setStyle("-fx-background-color: black");
                }
                break;
        }
        ban = !ban;
    }

    public int palabrasEscritas(String cadena) {
        int pos;
        if (cadena.isEmpty()) { //si la cadena está vacía
            palabrasEscritas = 0;
        } else {
            pos = cadena.indexOf(" ");
            while (pos != -1) {
                palabrasEscritas++;
                pos = cadena.indexOf(" ", pos + 1);
            }
        }
        return palabrasEscritas;
    }
}

