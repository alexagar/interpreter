/*
 * Class:       CS 4308 Section 2
 * Term:        Spring 2022
 * Name:        Alexa Garcia
 * Instructor:   Sharon Perry
 * Project:     Deliverable P2 Parser
 * 
 * OPTION 2 WAS CHOSEN: 10 POINTS OFF FOR ONE WEEK EXTENSION
 */

import java.util.ArrayList;

public class driver {  
    public static void main(String[] args) throws Exception {
        //token table array
        String[] tokens = {"function", "if", "else", "while", "repeat", "print",
        "=","<=","<",">=",">","==","~=","+","-","*","/", "end", "then", "5019", "5020", "(", ")", "do"};

        //token descriptor table array
        String[] tokensID = {"FUNC_KEY", "IF_KEY", "ELSE_KEY", "WHILE_KEY", "REPEAT_KEY", "PRINT_KEY",
        "ASSIGN_OP", "LE_OP", "LT_OP", "GE_OP", "GT_OP", "EQ_OP", "NE_OP", "ADD_OP", "SUB_OP", "MUL_OP", 
        "DIV_OP", "END_KEY", "THEN_KEY", "LIT_INT", "ID", "OPEN_PARENTH", "CLOSE_PARENTH", "DO_KEY"};

        ArrayList<String> lines = new ArrayList<String>();

        //create scanner object to begin scanning
        scanner scan = new scanner(tokens, tokensID, "Test3.jl");
        scan.start_scan();                              //begin scanning
        lines = scan.getLines();                        //update lines variable to have scanned in tokens
        
        

        //create parser object to start parsing
        parser parse = new parser(lines);
        parse.start_parse();
        

    }
}
