package CompilerSPy;
import AST.*;
import java.util.*;
import Lexer.*;
import java.io.*;


public class Compiler {
    
      // compile must receive an input with an character less than 
      // p_input.lenght
   public Program compile( char []input, PrintWriter outError ) {
        
       symbolTable = new SymbolTable();
       error = new CompilerError( lexer, new PrintWriter(outError) );
       lexer = new Lexer(input, error);
       error.setLexer(lexer);
        
        
       Program p = null;
       try {
          lexer.nextToken();
	  System.out.print(" " + lexer.token + " ");
          if ( lexer.token == Symbol.EOF )
             error.show("Unexpected EOF");
          p = program();
          if ( lexer.token != Symbol.EOF ) {
             p = null;
             error.show("EOF expected");
          }
       } catch ( Exception e ) {
              // the below statement prints the stack of called methods.
              // of course, it should be removed if the compiler were 
              // a production compiler.
            e.printStackTrace();
          p = null;
       }
       
       return p;   
   }
    
   
   private Program program() {
       return null;
   }

    
    
   private SymbolTable symbolTable;
   private Lexer lexer;
   private CompilerError error;
   
}
