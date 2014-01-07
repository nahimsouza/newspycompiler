package Lexer;

import CompilerSPy.CompilerError;
import java.util.HashMap;

public class Lexer {

    /*
     * Esta classe faz a análise léxica.
     */
    public Lexer(char[] input, CompilerError error) {
        this.input = input;
        // add an end-of-file label to make it easy to do the lexer
        input[input.length - 1] = '\0';
        // number of the current line
        lineNumber = 1;
        tokenPos = 0;
        lastTokenPos = 0;
        beforeLastTokenPos = 0;
        this.error = error;
    }
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
    }

    public void nextToken() {
        char ch;
        int indentCount = 0;

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

        //Atualiza Indent
        indentCount = lastIndentCount;

        // Pula os espacos em branco
        lastTokenPos = tokenPos;
        while ((ch = input[tokenPos]) == ' ' || ch == '\r'
                || ch == '\t' || ch == '\n') {

            // count the number of lines
            if (ch == '\n') {
                indentCount = 0;
                linhaIndent = 0;
                lineNumber++;
            }

            //verifica se é IDENT
            if (ch == '\t' && linhaIndent == 0) {
                indentCount++;
            }

            tokenPos++;
        }

        linhaIndent = 1;

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
        lastIndentCount = indentCount;

        if (ch == '\0') {
            token = Symbol.EOF;
        } else if (input[tokenPos] == '#') {
            // pula os comentarios:
            while (input[tokenPos] != '\n' && input[tokenPos] != '\0') {
                tokenPos++;
            }
            nextToken();
        } else {
            if (Character.isLetter(ch)) {
                // get an identifier or keyword
                StringBuffer ident = new StringBuffer();
                while (Character.isLetter(input[tokenPos])
                        || Character.isDigit(input[tokenPos])) {
                    ident.append(input[tokenPos]);
                    tokenPos++;
                }
                stringValue = ident.toString();
                // verifica se é uma palavra-chave
                Object value = keywordsTable.get(stringValue);
                if (value == null) {
                    // se nao for uma palavra-chave, significa que é uma variavel
                    token = Symbol.ID;
                } else {
                    token = (Integer) value;
                }
            } else if (Character.isDigit(ch)) {
                StringBuffer number = new StringBuffer();
                while (Character.isDigit(input[tokenPos])) {
                    number.append(input[tokenPos]);
                    tokenPos++;
                }
                // encontrou um numero
                token = Symbol.NUM;
                try {
                    numberValue = Integer.valueOf(number.toString()).intValue();
                } catch (NumberFormatException e) {
                    error.show("Number out of limits");
                }
                if (numberValue >= MaxValueInteger) {
                    error.show("Number out of limits");
                }
            } else if (input[tokenPos] == '\'' || input[tokenPos] == '"') {
                String aux = "";
                char abre = input[tokenPos++];
                while (input[tokenPos] != abre || (input[tokenPos] == abre && input[tokenPos - 1] == '\\')) {
                    aux += input[tokenPos];
                    tokenPos++;
                }
                tokenPos++;
                token = Symbol.STRING;
                stringValue = aux;

            } else {
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

    public int getNumberValue() {
        return numberValue;
    }

    public String getLiteralStringValue() {
        return literalStringValue;
    }
    // current token
    public int token;
    private String stringValue, literalStringValue;
    private int numberValue;
    private int tokenPos;
    //  input[lastTokenPos] is the last character of the last token found
    private int lastTokenPos;
    //  input[beforeLastTokenPos] is the last character of the token before the last
    // token found
    private int beforeLastTokenPos;
    // program given as input - source code
    private char[] input;
    // number of current line. Starts with 1
    private int lineNumber;
    private CompilerError error;

    private int indent, dedent, lastIndentCount, linhaIndent;
}
