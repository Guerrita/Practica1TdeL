package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller {

    @FXML
    TextArea ta_file;


    public void searchFile() {
        File file;
        FileChooser choose = new FileChooser();
        choose.setTitle("Seleccionar archivo");

        // Agregar filtros para facilitar la búsqueda
        choose.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Java File","*.java")
        );

        file = choose.showOpenDialog(null);

        try {
            //Abro el stream, el fichero debe existir
            FileReader fr = new FileReader(file);

            //Leemos el fichero y lo mostramos por pantalla
            int valor = fr.read();
            String text = "";
            while(valor != -1) {
                text = text.concat(String.valueOf(((char) valor)));
                valor = fr.read();
            }

            //Cerramos el stream
            fr.close();
            ta_file.setText(text);
            System.out.print("Archivo leído correctamente");
        } catch (IOException e) {
            System.out.println("Error E/S: "+e);
        }
    }
    public void analyze() {}
}
