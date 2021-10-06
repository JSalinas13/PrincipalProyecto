package com.example.principalproyecto.views;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

public class Plantilla {
    public final String[] arImagenes = {"ElCazo.jpg", "ElAlacran.jpg", "ElBarril.jpg", "ElCatrin.jpg", "ElArpa.jpg", "ElMusico.jpg", "ElMundo.jpg", "ElNopal.jpg", "ElParaguas.jpg", "ElPino.jpg", "ElSol.jpg", "ElVenado.jpg", "LaAraña.jpg", "LaCalabera.jpg", "LaCampana.jpg", "LaChalupa.jpg", "LaDama.jpg", "LaEscalera.jpg", "LaEstrella.jpg", "LaMaceta.jpg", "LaMano.jpg", "LaPalma.jpg", "LaPera.jpg", "LaRana.jpg", "LaRosa.jpg", "LaSirena.jpg"};
    public Button[][] arBtnCartas = new Button[3][3];
    private File file;
    public int imagenUsada[] = new int[9];
    private Image imgCarta;
    private GridPane gpPlantilla;
    private int numeroGen = 0, contUsadas = 0, contBtn = 0;


    public Plantilla() {
        gpPlantilla = new GridPane();
    }

    public GridPane getPlantilla() {
        int cont = 0;
        imagenUsada = generarNumRandom();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                arBtnCartas[j][i] = new Button();
                arBtnCartas[j][i].setPrefSize(200, 220);
                file = new File("src/main/java/com/example/principalproyecto/images/" + arImagenes[imagenUsada[cont]]);
                imgCarta = new Image(file.toURI().toString());
                ImageView view = new ImageView(imgCarta);
                view.setFitHeight(120);
                view.setFitWidth(100);
                view.setPreserveRatio(true);
                arBtnCartas[j][i].setGraphic(view);

                gpPlantilla.add(arBtnCartas[j][i], j, i);
                cont++;
            }

        }
        return gpPlantilla;
    }

    int[] generarNumRandom() {
        int cantidad = 9, rango = 26, i = 0;
        imagenUsada[0] = (int) (Math.random() * rango);
        for (i = 1; i < cantidad; i++) {
            imagenUsada[i] = (int) (Math.random() * rango);
            for (int j = 0; j < i; j++) {
                if (imagenUsada[i] == imagenUsada[j])
                    i--;
            }
        }
        return imagenUsada;
    }

}
