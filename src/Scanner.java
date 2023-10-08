import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",  TipoToken.WHILE);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();
    
    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        int estado = 0;
        String lexema = "";
        char c;
        int numLinea=0;

        for(int i=0; i<source.length(); i++){
            c = source.charAt(i);

            switch (estado){
                 case 0:
                    if (c == '>') {
                        estado = 1;
                        lexema += c;
                    } else if (c == '<') {
                        estado = 4;
                        lexema += c;
                    } else if (c == '=') {
                        estado = 7;
                        lexema += c;
                    } else if (c == '!') {
                        estado = 10;
                        lexema += c;
                    } else if (Character.isLetter(c)) {
                        estado = 13;
                        lexema += c;
                    } else if (Character.isDigit(c)) {
                        estado = 15;
                        lexema += c;
                    } else if (c == '/') {
                        estado = 26;
                        lexema += c;
                    } else if (c == '(') {
                        Token t = new Token(TipoToken.LEFT_PAREN, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == ')') {
                        Token t = new Token(TipoToken.RIGHT_PAREN, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == '{') {
                        Token t = new Token(TipoToken.LEFT_BRACE, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == '}') {
                        Token t = new Token(TipoToken.RIGHT_BRACE, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == ',') {
                        Token t = new Token(TipoToken.COMMA, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == '.') {
                        Token t = new Token(TipoToken.DOT, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == '-') {
                        Token t = new Token(TipoToken.MINUS, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == '+') {
                        Token t = new Token(TipoToken.PLUS, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    } else if (c == ';') {
                        Token t = new Token(TipoToken.SEMICOLON, String.valueOf(c));
                        tokens.add(t);
                        lexema = "";
                        estado = 0;
                    }
                    break;
                case 1:
                    if(c == '='){ //No se manda al estado 2 porque es un terminal
                        lexema += c;
                        Token t2 = new Token(TipoToken.GREATER_EQUAL, lexema);
                        tokens.add(t2);
                        estado = 0;
                        lexema = "";
                    }
                    else{ //No se manda al estado 3 porque es un terminal
                        Token t3 = new Token(TipoToken.GREATER, lexema);
                        tokens.add(t3);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 4:
                    if(c == '='){ //No se manda al estado 5 porque es un terminal
                        lexema +=c;
                        Token t5 = new Token(TipoToken.LESS_EQUAL, lexema);
                        tokens.add(t5);
                        estado = 0;
                        lexema = "";
                    }
                    else{ //No se manda al estado 6 porque es un terminal
                        Token t6 = new Token(TipoToken.LESS, lexema);
                        tokens.add(t6);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 7:
                    if(c == '='){ //No se manda al estado 8 porque es un terminal
                        lexema +=c;
                        Token t8 = new Token(TipoToken.EQUAL_EQUAL, lexema);
                        tokens.add(t8);
                        estado = 0;
                        lexema = "";
                    }
                    else{
                        Token t9 = new Token(TipoToken.EQUAL, lexema);
                        tokens.add(t9);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                case 10:
                    if(c == '='){
                        lexema +=c;
                        Token t8 = new Token(TipoToken.EQUAL_EQUAL, lexema);
                        tokens.add(t8);
                        estado = 0;
                        lexema = "";
                    }
                    else{
                        Token t9 = new Token(TipoToken.EQUAL, lexema);
                        tokens.add(t9);
                        estado = 0;
                        lexema = "";
                        i--;
                }
                case 13:
                    if(Character.isLetterOrDigit(c)){
                        estado = 13;
                        lexema += c;
                    }
                    else{
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if(tt == null){
                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        }
                        else{
                            Token t = new Token(tt, lexema);
                            tokens.add(t);
                        }

                        estado = 0;
                        lexema = "";
                        i--;

                    }
                    break;

                case 15:
                    if(Character.isDigit(c)){
                        estado = 15;
                        lexema += c;
                    }
                    else if(c == '.'){

                    }
                    else if(c == 'E'){

                    }
                    else{
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;


                    case 18:
                    if (c == '+' || c == '-') {
                        // Se espera un signo, cambia al estado 19 y agrega el carácter al lexema
                        estado = 19;
                        lexema += c;
                    } else if (Character.isDigit(c)) {
                        // Se espera un dígito, cambia al estado 20 y agrega el carácter al lexema
                        estado = 20;
                        lexema += c;
                    } else {
                        // Error: Se esperaba un signo o un dígito
                        Interprete.error(numLinea, "Se esperaba un signo o un digito");
                    }
                    break;

                    case 19:
                    if (Character.isDigit(c)) {
                        // Continuación de un número después de un signo, cambia al estado 20 y agrega el carácter al lexema
                        estado = 20;
                        lexema += c;
                    } else {
                        // Error: Se esperaba un dígito
                        Interprete.error(numLinea, "Se esperaba un digito");
                    }
                    break;

                    case 20:
                    if (Character.isDigit(c)) {
                        // Parte principal de un número, agrega el carácter al lexema y continúa en el mismo estado
                        estado = 20;
                        lexema += c;
                    } else {
                        // Crear un token de tipo NUMBER, agregarlo a la lista de tokens y reiniciar el estado y el lexema
                        Token t21 = new Token(TipoToken.NUMBER, lexema, Double.parseDouble(lexema));
                        tokens.add(t21);
                        estado = 0;
                        lexema = "";
                        i--;  // Retroceder el índice para analizar el siguiente carácter
                    }
                    break;
                    
                    case 24:
                    if (c == '\n') {
                        // Fin de línea, termina el análisis de la cadena
                        i = source.length();
                    } else if (c == '"') {
                        // Fin de la cadena, crear un token de tipo STRING y reiniciar el estado y el lexema
                        lexema += c;
                        Token t25 = new Token(TipoToken.STRING, lexema, lexema.substring(1, lexema.length() - 1));
                        tokens.add(t25);
                        estado = 0;
                        lexema = "";
                    } else {
                        // Continuación de la cadena, agrega el carácter al lexema y continúa en el mismo estado
                        estado = 24;
                        lexema += c;
                    }
                    break;

                    case 26:
                    if(c == '*'){
                        estado = 27;
                        lexema += c;
                    }
                    else if(c == '/'){
                        estado = 30;
                        lexema += c;
                    }
                    else{
                          Token t26 = new Token(TipoToken.SLASH, lexema);
                          tokens.add(t26);
                          estado = 0;
                          lexema = "";
                          i--;
                    }                            
                  break;
                  
                case 27: 
                    if(c == '*'){
                        estado = 28;
                        lexema += c;
                    }
                    else{
                        estado = 27;
                        lexema += c;
                    }
                  break;
                    
                case 28: 
                    if(c == '*'){
                        estado = 28;
                        lexema +=c;
                        }
                    else if(c == '/'){
                        estado = 0;
                        lexema = "";
                    }
                    else{
                       estado = 27;
                       lexema +=c;
                    }
                  break;
                  
                case 30:
                    if( c =='\n' ){
                        estado = 0;
                        lexema ="";
                    }
                    else {
                        estado = 30;
                        lexema += c;
                    }       
                break;
            }
        }
    
        return tokens;
    }
}
