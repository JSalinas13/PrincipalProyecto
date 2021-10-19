package com.example.principalproyecto.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class Loteria extends Stage implements EventHandler<ActionEvent> {

    private final String[] arImagenes = {"ElCazo.jpg", "ElAlacran.jpg", "ElBarril.jpg", "ElCatrin.jpg", "ElArpa.jpg", "ElMusico.jpg", "ElMundo.jpg", "ElNopal.jpg", "ElParaguas.jpg", "ElPino.jpg", "ElSol.jpg", "ElVenado.jpg", "LaAraña.jpg", "LaCalabera.jpg", "LaCampana.jpg", "LaChalupa.jpg", "LaDama.jpg", "LaEscalera.jpg", "LaEstrella.jpg", "LaMaceta.jpg", "LaMano.jpg", "LaPalma.jpg", "LaPera.jpg", "LaRana.jpg", "LaRosa.jpg", "LaSirena.jpg"};
    private Scene scene;
    private VBox vBox;
    private HBox hBox1, hBox2;
    private Button btnAtras, btnSiguiente, btnRegresar;
    private File file, fileAlert;
    private Image imgCarta, imgAlert;
    private Label lbCarta, lbContador, lbTiempo;
    Plantilla arPlantillas[] = new Plantilla[5];
    private GridPane gridPanes[] = new GridPane[5];
    private int masoCartas[] = new int[arImagenes.length];
    int ordenadoImagenes[];
    int cont = 0, noCarta = 1, contCartas = 0, tiempoGo = 0, tiempoAtras = 3, contGanar = 0;
    Timer timer;

    public Loteria() {
        CrearUI();
        this.setTitle("Loteria");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        MostrarMensajeBien();
        getScece();
        System.out.println("Longitud de imagenes " + arPlantillas[cont].arImagenes.length);
        ordenadoImagenes = new int[arPlantillas[cont].imagenUsada.length];
        for (int i = 0; i < 9; i++) {
            ordenadoImagenes[i] = arPlantillas[cont].imagenUsada[i];
        }
        ordenarArregloPorInt(ordenadoImagenes);

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(contCartas);
                        if (tiempoGo == 3) {

                            btnAtras.setDisable(true);
                            btnSiguiente.setDisable(true);

                            if (contCartas < arPlantillas[cont].arImagenes.length) {
                                file = new File("src/main/java/com/example/principalproyecto/images/" + arPlantillas[cont].arImagenes[masoCartas[contCartas]]);
                                imgCarta = new Image(file.toURI().toString());
                                ImageView view = new ImageView(imgCarta);
                                view.setFitWidth(180);
                                view.setFitHeight(200);
                                view.setPreserveRatio(true);
                                lbCarta.setGraphic(view);
                                lbTiempo.setText("Comenzamos.......");

                                hBox2.getChildren().removeAll(gridPanes[cont], lbCarta);
                                hBox2.getChildren().addAll(gridPanes[cont], lbCarta);

                                lbContador.setText("Plantilla: " + noCarta);
                                hBox1.getChildren().removeAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                                hBox1.getChildren().addAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                                contCartas += 1;
                            } else {
                                if (contGanar != 9) {
                                    ((Timer) e.getSource()).stop();
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle(null);
                                    alert.setHeaderText(null);
                                    alert.setContentText(null);
                                    fileAlert = new File("src/main/java/com/example/principalproyecto/imagesExternas/lost.jpg");
                                    imgAlert = new Image(fileAlert.toURI().toString());
                                    ImageView view = new ImageView(imgAlert);
                                    alert.setGraphic(view);
                                    alert.showAndWait();

                                    System.out.println("Perdiste");
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                        } else {
                            tiempoAtras -= 1;
                            hBox2.getChildren().removeAll(gridPanes[cont], lbCarta);
                            hBox2.getChildren().addAll(gridPanes[cont], lbCarta);

                            lbContador.setText("Plantilla: " + noCarta);
                            lbTiempo.setText("Comieza en: " + tiempoAtras);
                            hBox1.getChildren().removeAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                            hBox1.getChildren().addAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                            tiempoGo += 1;
                        }
                    }
                });
            }
        });
        timer.start();
    }


    @Override
    public void handle(ActionEvent event) {
        //Cunado se presiona el boton siguiente o ▶
        if (event.getSource() == btnSiguiente) {
            cont += 1;
            if (cont < 5) {
                ordenadoImagenes = new int[arPlantillas[cont].imagenUsada.length];
                for (int i = 0; i < 9; i++) {
                    ordenadoImagenes[i] = arPlantillas[cont].imagenUsada[i];
                }
                ordenarArregloPorInt(ordenadoImagenes);
                noCarta += 1;
                file = new File("src/main/java/com/example/principalproyecto/images/" + arImagenes[masoCartas[0]]);
                imgCarta = new Image(file.toURI().toString());
                ImageView view = new ImageView(imgCarta);
                view.setFitWidth(180);
                view.setFitHeight(200);
                view.setPreserveRatio(true);
                lbCarta.setGraphic(view);

                lbContador.setText("Plantilla: " + noCarta);
                lbTiempo.setText("Comieza en: " + tiempoAtras);
                hBox1.getChildren().removeAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                hBox1.getChildren().addAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);

                hBox2.getChildren().removeAll(gridPanes[cont - 1], lbCarta);
                hBox2.getChildren().addAll(gridPanes[cont], lbCarta);
            } else {
                cont = 0;
                noCarta = 1;

                ordenadoImagenes = new int[arPlantillas[cont].imagenUsada.length];
                for (int i = 0; i < 9; i++) {
                    ordenadoImagenes[i] = arPlantillas[cont].imagenUsada[i];
                }
                ordenarArregloPorInt(ordenadoImagenes);
                lbContador.setText("Plantilla: " + noCarta);
                lbTiempo.setText("Comieza en: " + tiempoAtras);
                hBox1.getChildren().removeAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                hBox1.getChildren().addAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);

                hBox2.getChildren().removeAll(gridPanes[(arPlantillas.length - 1)], lbCarta);
                hBox2.getChildren().addAll(gridPanes[cont], lbCarta);
            }
            System.out.println("Cont: " + cont);
            System.out.println("Carat avanzo: " + noCarta);
        }

        //Cunado se presiona el boton atras o ◀
        if (event.getSource() == btnAtras) {
            if (cont > 0) {
                cont -= 1;
                noCarta -= 1;
                ordenadoImagenes = new int[arPlantillas[cont].imagenUsada.length];
                for (int i = 0; i < 9; i++) {
                    ordenadoImagenes[i] = arPlantillas[cont].imagenUsada[i];
                }
                ordenarArregloPorInt(ordenadoImagenes);
                lbContador.setText("Plantilla: " + noCarta);
                lbTiempo.setText("Comieza en: " + tiempoAtras);
                hBox1.getChildren().removeAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                hBox1.getChildren().addAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);

                hBox2.getChildren().removeAll(gridPanes[cont + 1], lbCarta);
                hBox2.getChildren().addAll(gridPanes[cont], lbCarta);
            } else {
                cont = arPlantillas.length - 1;
                noCarta = 5;
                ordenadoImagenes = new int[arPlantillas[cont].imagenUsada.length];
                for (int i = 0; i < 9; i++) {
                    ordenadoImagenes[i] = arPlantillas[cont].imagenUsada[i];
                }
                ordenarArregloPorInt(ordenadoImagenes);
                lbContador.setText("Plantilla: " + noCarta);
                lbTiempo.setText("Comieza en: " + tiempoAtras);
                hBox1.getChildren().removeAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                hBox1.getChildren().addAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
                hBox2.getChildren().removeAll(gridPanes[0], lbCarta);
                hBox2.getChildren().addAll(gridPanes[cont], lbCarta);
            }
            System.out.println("Cont: " + cont);
            System.out.println("Carat avanzo: " + noCarta);

        }

        //Cunado se presiona el boton regresar
        if (event.getSource() == btnRegresar) {
            timer.stop();
            this.close();
        }

        if (event.getSource() == arPlantillas[cont].arBtnCartas[0][0]) {
            unableButtons(0, 0, 0);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[1][0]) {
            unableButtons(1, 0, 1);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[2][0]) {
            unableButtons(2, 0, 2);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[0][1]) {
            unableButtons(0, 1, 3);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[1][1]) {
            unableButtons(1, 1, 4);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[2][1]) {
            unableButtons(2, 1, 5);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[0][2]) {
            unableButtons(0, 2, 6);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[1][2]) {
            unableButtons(1, 2, 7);
        }
        if (event.getSource() == arPlantillas[cont].arBtnCartas[2][2]) {
            unableButtons(2, 2, 8);
        }


    }

    public void MostrarMensajeBien() {


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loteria mexicana");
        alert.setHeaderText(null);
        alert.setContentText("\"Loteria Mexicana\"\nEn México, la lotería es un juego de azar parecido al bingo que se juega con un mazo de cartas en vez de números. " +
                "\nLa baraja se compone de 54 imágenes diferentes, que incluyen objetos y personajes como la sirena, el catrín, el barril, la estrella y el nopal. " +
                "\nEl propósito del juego es llenar una tabla (conocida también como “cartón”) elegida por el jugador." +
                "\nLa primera persona en completar su tabla grita “¡lotería!”, dando fin a la ronda.");
        alert.setWidth(500);
        alert.setHeight(500);

        fileAlert = new File("src/main/java/com/example/principalproyecto/imagesExternas/FondoAlert.jpg");
        imgAlert = new Image(fileAlert.toURI().toString());
        ImageView view = new ImageView(imgAlert);
        view.setFitWidth(180);
        view.setFitHeight(200);
        view.setPreserveRatio(true);
        alert.setGraphic(view);
        alert.showAndWait();
    }

    void getScece() {
        btnAtras = new Button("      ◀      ");
        btnSiguiente = new Button("      ▶      ");
        lbCarta = new Label();
        lbContador = new Label();
        lbContador.setId("lb_Contador");
        lbTiempo = new Label();
        lbTiempo.setId("lb_Tiempo");
        vBox = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        btnRegresar = new Button("Regresar");
        btnRegresar.setId("btn_Regresar");
        masoCartas = generarMaso();

        arPlantillas[0] = new Plantilla();
        arPlantillas[1] = new Plantilla();
        arPlantillas[2] = new Plantilla();
        arPlantillas[3] = new Plantilla();
        arPlantillas[4] = new Plantilla();

        for (int i = 0; i < 5; i++) {
            gridPanes[i] = new GridPane();
            gridPanes[i] = arPlantillas[i].getPlantilla();
        }

        btnSiguiente.setOnAction(this);
        btnAtras.setOnAction(this);
        btnRegresar.setOnAction(this);
        for (int i = 0; i < 5; i++) {
            accionarBoton(i);
        }

        lbContador.setText("Plantilla: " + noCarta);
        lbTiempo.setText("Comieza en: " + tiempoAtras);
        hBox1.getChildren().addAll(btnAtras, btnSiguiente, lbContador, lbTiempo, btnRegresar);
        hBox1.setSpacing(20);

        file = new File("src/main/java/com/example/principalproyecto/images/" + arImagenes[masoCartas[contCartas]]);
        imgCarta = new Image(file.toURI().toString());
        ImageView view = new ImageView(imgCarta);
        view.setFitWidth(180);
        view.setFitHeight(200);
        view.setPreserveRatio(true);
        lbCarta.setGraphic(view);

        hBox2.getChildren().addAll(gridPanes[cont], lbCarta);
        hBox2.setSpacing(10);
        vBox.setStyle("-fx-background-color: #000;");
        vBox.getChildren().addAll(hBox1, hBox2);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        scene = new Scene(vBox, 500, 600);

        file = new File("src/main/java/com/example/principalproyecto/css/style.css");
        scene.getStylesheets().add(file.toURI().toString());

    }

    public int[] generarMaso() {
        int i = 0, cantidad = arImagenes.length, rango = 26;
        int masoCartad[] = new int[arImagenes.length];

        for (i = 1; i < cantidad; i++) {
            masoCartad[i] = (int) (Math.random() * rango);
            for (int j = 0; j < i; j++) {
                if (masoCartad[i] == masoCartad[j])
                    i--;
            }
        }

        return masoCartad;
    }

    boolean busquedaImagen(int imagenMaso, int imagenUsada[]) {
        int li = 0, ls = 0, pm = 0;
        ls = imagenUsada.length - 1;
        do {
            pm = (ls + li) / 2;
            if (imagenUsada[pm] > imagenMaso) {
                ls = pm - 1;
            } else {
                li = pm + 1;
            }
        } while (imagenUsada[pm] != imagenMaso && li <= ls);

        if (imagenUsada[pm] == imagenMaso) {
//            System.out.println("La imagen esta en la plantilla" +
//                    "\nImagen encontrada: " + imagenUsada[pm]);
            return true;
        } else {
//            System.err.println("La imagen no esta en la plantilla" +
//                    "\nImagen no encontrada: " + imagenUsada[pm]);
            return false;
        }
    }

    int[] ordenarArregloPorInt(int arreglo[]) {
        for (int i = 0; i < (arreglo.length - 1); i++) {
            for (int k = 0; k < (arreglo.length - 1); k++) {
                if (arreglo[k] > arreglo[k + 1]) {
                    int aux = arreglo[k];
                    arreglo[k] = arreglo[k + 1];
                    arreglo[k + 1] = aux;
                }
            }
        }
        return arreglo;
    }

    void unableButtons(int j, int i, int pos) {
        if (contCartas > 0) {
            if (busquedaImagen(masoCartas[(contCartas - 1)], ordenadoImagenes)) {
                if (masoCartas[(contCartas - 1)] == arPlantillas[cont].imagenUsada[pos]) {
                    arPlantillas[cont].arBtnCartas[j][i].setDisable(true);
                    contGanar += 1;
                    System.out.println("Ganar: " + contGanar);
                    if (contGanar == 9) {
                        timer.stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText(null);
                        fileAlert = new File("src/main/java/com/example/principalproyecto/imagesExternas/win.jpg");
                        imgAlert = new Image(fileAlert.toURI().toString());
                        ImageView view = new ImageView(imgAlert);
                        alert.setGraphic(view);
                        alert.showAndWait();

                        System.out.println("GANASTEEEEEEEEEEEE");

                    }

                }
            }

        }
    }

    void accionarBoton(int num) {
        int id = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arPlantillas[num].arBtnCartas[j][i].setOnAction(this);
                arPlantillas[num].arBtnCartas[j][i].setId("btn_Cartas" + id);
                id++;
            }
        }
    }
}





