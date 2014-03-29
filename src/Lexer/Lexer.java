/**
 * Lab de Compiladores - BCC, UFSCar, Sorocaba - 2013
 *
 * SPy Compiler 
 * Fernando Villas Boas Alves 
 * Nahim Alves de Souza
 *
 * ====================================================
 *
 * Classe Lexer (adaptada de Tomazella & Casadei): 
 *  Contem as funcoes necessarias para a analise lexica, 
 *  alem da tabela de palavras-chave.
 *
 */
package Lexer;

import CompilerSPy.CompilerError;
import java.util.HashMap;

public class Lexer {

    // ================= DECLARACAO DE VARIAVEIS ====================
    // current token
    public int token;

    private String stringValue;
    // private String literalStringValue;
    private double numberValue;
    private String numberType;
    
    private int tokenPos;

    //  input[lastTokenPos] is the last character of the last token found
    private int lastTokenPos;

    //  input[beforeLastTokenPos] is the last character of the token before the last token found
    private int beforeLastTokenPos;

    // program given as input - source code
    private char[] input;

    // number of current line. Starts with 1
    private int lineNumber;

    private CompilerError error;

    private int indent, dedent, lastIndentCount, linhaIndent;

    private static final int MaxValueInteger = 32767;

    // contains the keywords
    private static final HashMap<String, Integer> keywordsTable;

    // this code will be executed only once for each program execution
    static {
        keywordsTable = new HashMap();

        keywordsTable.put("print", Symbol.PRINT);
        keywordsTable.put("break", Symbol.BREAK);
        keywordsTable.put("continue", Symbol.CONTINUE);
        keywordsTable.put("return", Symbol.RETURN);
        keywordsTable.put("string", Symbol.STRING);
        keywordsTable.put("if", Symbol.IF);
        keywordsTable.put("elif", Symbol.ELIF);
        keywordsTable.put("else", Symbol.ELSE);
        keywordsTable.put("while", Symbol.WHILE);
        keywordsTable.put("for", Symbol.FOR);
        keywordsTable.put("range", Symbol.RANGE);
        keywordsTable.put("def", Symbol.DEF);
        keywordsTable.put("class", Symbol.CLASS);
        keywordsTable.put("self", Symbol.SELF);
        keywordsTable.put("not", Symbol.NOT);
        keywordsTable.put("is", Symbol.IS);
        keywordsTable.put("int", Symbol.INT);
        keywordsTable.put("float", Symbol.FLOAT);
    }

    // =========================================================
    public Lexer(char[] input, CompilerError error) {
        this.input = input;

        // add an end-of-file label to make it easy to do the lexer
        input[input.length - 1] = '\0';

        // number of the current line
        lineNumber = 1;
        tokenPos = 0;
        lastTokenPos = 0;
        beforeLastTokenPos = 0;

        // usados para o INDENT e DEDENT
        linhaIndent = 0;        // guarda 1 se a linha contem algum comando e 0 caso contrário
        lastIndentCount = 0;

        this.error = error;
    }

    public boolean comment() {
        if (input[tokenPos] == '#') {
            // enquanto tiver '\n' e nao tiver chegado no fim do arquivo
            while (input[tokenPos] != '\n' && tokenPos < (input.length - 1)) {
                tokenPos++;
            }
            nextToken();
            return true;
        }
        return false;
    }

    public void nextToken() {
        // Verifica se o codigo começa com um comentario
        if (comment()) {
            return;
        }

        // Para tratar o INDENT e DEDENT
        if (indent > 0) {
            token = Symbol.INDENT;
            indent = 0;
            return;
        }
        if (dedent > 0) {
            token = Symbol.DEDENT;
            dedent--;
            return;
        }

        // indentCount eh usado para verificar a identacao atual
        int indentCount = lastIndentCount;

        // Verifica os espacos em branco
        lastTokenPos = tokenPos;
        while ((tokenPos < input.length) && (input[tokenPos] == ' ' || input[tokenPos] == '\r' || input[tokenPos] == '\t' || input[tokenPos] == '\n')) {

            if (input[tokenPos] == '\n') {
                indentCount = 0; // zera o contador de indentacoes
                if (linhaIndent == 1) {
                    token = Symbol.NEWLINE;
                    linhaIndent = 0; // indica que a linha nao possui nenhum comando
                    return;
                }
            }

            // para contar se houve varios TABs seguidos
            if (input[tokenPos] == '\t' && linhaIndent == 0) {
                indentCount++;
            }

            tokenPos++;
        }

        // Pode ser que tenha um comentario depois de varios espacos em branco
        if (comment()) {
            return;
        }

        linhaIndent = 1; // indica que tem algum codigo depois dos espacos em branco

        /* INDENT/DEDENT: 
         - O IF verifica se eh um INDENT (1º caso) ou DEDENT(2º caso)
         - Quando entra em uma das condicoes, conta quantos INDENTs ou DEDENTs foram acrescentados
         - Chama nextToken(), que por sua vez, cai no primeiro IF (indent > 0) 
         e retorna o Symbol.INDENT (de modo equivalente para o DEDENT)
         */
        if (indentCount > lastIndentCount) {
            indent = indentCount - lastIndentCount;
            nextToken();
            lastIndentCount = indentCount;
            return;
        } else if (indentCount < lastIndentCount) {
            dedent = lastIndentCount - indentCount;
            nextToken();
            lastIndentCount = indentCount;
            return;
        }
        lastIndentCount = indentCount; // ( eh necessario ?)

        // Verifica se eh fim do arquivo
        if (input[tokenPos] == '\0') {
            token = Symbol.EOF;
        } else {

            if (Character.isLetter(input[tokenPos]) || input[tokenPos] == '_') {
                // identificador pode comecar com letra ou underscore, e pode ter digitos
                String aux = "";
                while (Character.isLetter(input[tokenPos]) || Character.isDigit(input[tokenPos]) || input[tokenPos] == '_') {
                    aux += (input[tokenPos]);
                    tokenPos++;
                }

                stringValue = aux;
                
                // verifica se é uma palavra-chave
                Object value = keywordsTable.get(stringValue);
                if (value == null) {
                    // se nao for uma palavra-chave, significa que é uma variavel
                    token = Symbol.NAME;
                } else {
                    token = (Integer) value;
                }

            } else if (Character.isDigit(input[tokenPos])) {
                // caso encontre um numero
                String number = "";
                while (Character.isDigit(input[tokenPos]) || input[tokenPos] == '.') {
                    number += input[tokenPos];
                    tokenPos++;
                }

                if (Character.isLetter(input[tokenPos])) {
                    error.show("Number followed by a letter", true);
                }

                // encontrou um numero verifica se eh um inteiro valido
                token = Symbol.NUM;
                
                // Baseado em Angiolucci & Marucci
                try {
                    Integer temp = Integer.parseInt(number.toString());
                    numberValue = temp.doubleValue();
                    numberType = "int";
                } catch (NumberFormatException ex1) {
                    try {
                        numberValue = Float.parseFloat(number.toString());
                        numberType = "float";
                    } catch (NumberFormatException ex2) {
                        error.show("Value: " + numberValue + " is an invalid number.", true);
                    }
                }

                // MaxValueInteger = 32767 - nao tinha na especificacao, deixei pq ja tinha no codigo 
                if (numberValue >= MaxValueInteger || numberValue <= -MaxValueInteger) {
                    error.show("Number out of limits");
                }
            } else if (input[tokenPos] == '\'' || input[tokenPos] == '"') {
                // caso encontre uma STRING (qqr texto entre aspas)
                String aux = "";

                char abre = input[tokenPos++]; // abre aspas pode ser ' ou "
                while (input[tokenPos] != abre || (input[tokenPos] == abre && input[tokenPos - 1] == '\\')) {
                    aux += input[tokenPos];
                    tokenPos++;
                }
                tokenPos++;
                token = Symbol.STRING;
                stringValue = aux;
            } else {
                // switch case para os demais simbolos

                char ch = input[tokenPos]; // apenas para facilitar
                tokenPos++;
                switch (ch) {
                    case '+':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.PLUSASSIGN;
                        } else {
                            token = Symbol.PLUS;
                        }
                        break;
                    case '-':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.MINUSASSIGN;
                        } else {
                            token = Symbol.MINUS;
                        }
                        break;
                    case '*':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.MULTIASSIGN;
                        } else {
                            token = Symbol.MULT;
                        }
                        break;
                    case '/':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.DIVASSIGN;
                        } else if (input[tokenPos] == '/') {
                            tokenPos++;
                            token = Symbol.FLOORDIV;
                        } else {
                            token = Symbol.DIV;
                        }
                        break;
                    case '%':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.MODASSIGN;
                        } else {
                            token = Symbol.MOD;
                        }
                        break;
                    case '<':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.LE;
                        } else if (input[tokenPos] == '>') {
                            tokenPos++;
                            token = Symbol.NEQ;
                        } else {
                            token = Symbol.LT;
                        }
                        break;
                    case '>':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.GE;
                        } else {
                            token = Symbol.GT;
                        }
                        break;
                    case '=':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.EQ;
                        } else {
                            token = Symbol.ASSIGN;
                        }
                        break;
                    case '!':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.NEQC;
                        } else {
                            error.show("Invalid Character: '" + ch + "'", false);
                        }
                        break;
                    case '^':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.XORASSIGN;
                        } else {
                            token = Symbol.XOR;
                        }
                        break;
                    case '&':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.ANDASSIGN;
                        } else if (input[tokenPos] == '&') {
                            tokenPos++;
                            token = Symbol.AND;
                        } else {
                            token = Symbol.EC;
                        }
                        break;
                    case '|':
                        if (input[tokenPos] == '=') {
                            tokenPos++;
                            token = Symbol.ORASSIGN;
                        } else if (input[tokenPos] == '|') {
                            tokenPos++;
                            token = Symbol.OR;
                        } else {
                            token = Symbol.ORBAR;
                        }
                        break;
                    case '~':
                        token = Symbol.INVERTION;
                        break;
                    case '(':
                        token = Symbol.LEFTPAR;
                        break;
                    case ')':
                        token = Symbol.RIGHTPAR;
                        break;
                    case ',':
                        token = Symbol.COMMA;
                        break;
                    case ';':
                        token = Symbol.SEMICOLON;
                        break;
                    case ':':
                        token = Symbol.COLON;
                        break;
                    case '[':
                        token = Symbol.LEFTCURBRACKET;
                        break;
                    case ']':
                        token = Symbol.RIGHTCURBRACKET;
                        break;
                    case '.':
                        token = Symbol.DOT;
                        break;
                    default:
                        error.show("Invalid Character: '" + ch + "'", false);
                }
            }
        }
        beforeLastTokenPos = lastTokenPos;
    }

    // return the line number of the last token got with getToken()
    public int getLineNumber() {
        return lineNumber;
    }

    public int getLineNumberBeforeLastToken() {
        return getLineNumber(lastTokenPos);
    }

    private int getLineNumber(int index) {
        // return the line number in which the character input[index] is
        int i, n, size;
        n = 1;
        i = 0;
        size = input.length;
        while (i < size && i < index) {
            if (input[i] == '\n') {
                n++;
            }
            i++;
        }
        return n;
    }

    public String getCurrentLine() {
        //return getLine(lastTokenPos);
        return getLine(tokenPos);
    }

    public String getLineBeforeLastToken() {
        return getLine(beforeLastTokenPos);
    }

    private String getLine(int index) {
        // get the line that contains input[index]. Assume input[index] is at a token, not
        // a white space or newline

        int i;
        if (input.length <= 1) {
            return "";
        }
        i = index;
        if (i <= 0) {
            i = 1;
        } else if (i >= input.length) {
            i = input.length;
        }

        StringBuffer line = new StringBuffer();
        // go to the beginning of the line
        while (i >= 1 && input[i] != '\n') {
            i--;
        }
        if (input[i] == '\n') {
            i++;
        }
        // go to the end of the line putting it in variable line
        while (input[i] != '\0' && input[i] != '\n' && input[i] != '\r') {
            line.append(input[i]);
            i++;
        }
        return line.toString();
    }

    public String getStringValue() {
        return stringValue;
    }

    public double getNumberValue() {
        return numberValue;
    }
    
    public String getNumberType() {
        return numberType;
    }

    
//    public String getLiteralStringValue() {
//        return literalStringValue;
//    }
    
}
