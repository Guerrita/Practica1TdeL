package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import model.Lexer;
import model.Token;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static model.Token.Tipos.*;

public class Controller {

    @FXML
    TextArea file, tokens;


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
            FileReader fr = new FileReader(file);
            int valor = fr.read();
            String text = "";
            while(valor != -1) {
                text = text.concat(String.valueOf(((char) valor)));
                valor = fr.read();
            }
            fr.close();
            this.file.setText(text);
            JOptionPane.showMessageDialog(null,"Archivo leído correctamente");
        } catch (IOException e) {
            System.out.println("Error E/S: "+e);
        }
    }
    public void analyze() {
        String text = file.getText();
        String resultado = "";
        if (!text.isEmpty()) {
            ArrayList<String> lines = this.splitLines(text);
            for (String linea:lines) {
                ArrayList<Token> tokenizador = Lexer.lex(linea);
                for (Token token : tokenizador) {
                    if (token.getTipo() == IDENTIFY) {
                        resultado = resultado + "[ identificador: " + token.getValor() + " ]" + "->";
                        continue;
                    }
                    if (token.getTipo() == NUMCONST)resultado= resultado + "[ numero constante: " + token.getValor() + " ]" + "->";
                    if (token.getTipo() == OPERATOR)resultado= resultado + "[ operador: " + token.getValor() + " ]" + "->";
                    if (token.getTipo() == SEPARATOR)resultado= resultado + "[ separador: " + token.getValor() + " ]" + "->";
                    if (token.getTipo() == CHARCONST)resultado= resultado + "[ cadena de texto: " + token.getValor() + " ]" + "->";
                    if (token.getTipo() == KEYWORD)resultado= resultado + "[ keyword: " + token.getValor() + " ]" + "->";

                }
                if (resultado.endsWith("->")) {
                    int pos = resultado.lastIndexOf("->");
                    resultado = resultado.substring(0, pos);
                }
                resultado = resultado +"\n";
            }







            tokens.setText(resultado);

        } else {
            JOptionPane.showMessageDialog(null, "No ingreso ningun texto a analizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<String> splitLines(String text){
        String[] linesAux = text.split("\n");
        return new ArrayList<>(Arrays.asList(linesAux));
    }
}
