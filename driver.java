/*
 * Class:       CS 4308 Section 2
 * Term:        Spring 2022
 * Name:        Alexa Garcia
 * Instructor:   Sharon Perry
 * Project:     Deliverable P2 Parser
 */

import java.util.ArrayList;

public class driver {  
    public static void main(String[] args) throws Exception {
        //token table array
        String[] tokens = {"function", "if", "else", "while", "for", "print",
        "=","<=","<",">=",">","==","~=","+","-","*","/", "end"};

        //token descriptor table array
        String[] tokensID = {"FUNC_KEY", "IF_KEY", "ELSE_KEY", "WHILE_KEY", "FOR_KEY", "PRINT_KEY",
        "ASSIGN_OP", "LE_OP", "LT_OP", "GE_OP", "GT_OP", "EQ_OP", "NE_OP", "ADD_OP", "SUB_OP", "MUL_OP", "DIV_OP", "END_KEY"};

        ArrayList<String> lines = new ArrayList<String>();

        //create scanner object to begin scanning
        scanner scan = new scanner(tokens, tokensID);
        scan.start_scan();
        lines = scan.getLines();
        
        System.out.println("...End Scanner\n\n\n\nBeginning Parser...\n");

        parser parse = new parser(lines);
        parse.start_parse();
        
        
    }
}
