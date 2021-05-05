package model;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Lexer {

    public static ArrayList<Token> lex(String input) {
        final ArrayList<Token> tokens = new ArrayList<>();
        final StringTokenizer st = new StringTokenizer(input);

        while(st.hasMoreTokens()) {
            String palabra = st.nextToken();
            boolean matched = false;

            for (Token.Tipos tokenTipo : Token.Tipos.values()) {
                Pattern patron = Pattern.compile(tokenTipo.patron);
                Matcher matcher = patron.matcher(palabra);

                if(matcher.find()) {
                    Token tk = new Token();
                    tk.setTipo(tokenTipo);
                    tk.setValor(palabra);
                    tokens.add(tk);
                }
            }
        }
        return tokens;
    }

}