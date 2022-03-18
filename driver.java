/*
 * Class:       CS 4308 Section 2
 * Term:        Spring 2022
 * Name:        Alexa Garcia
 * Instructor:   Sharon Perry
 * Project:     Deliverable P2 Parser
 */

import java.util.ArrayList;
import java.io.*;

public class driver {  
    public static void main(String[] args) throws Exception{
        //token table array
        String[] tokens = {"function", "if", "else", "while", "for", "print",
        "=","<=","<",">=",">","==","~=","+","-","*","/", "end"};

        //token descriptor table array
        String[] tokensID = {"FUNC_KEY", "IF_KEY", "ELSE_KEY", "WHILE_KEY", "FOR_KEY", "PRINT_KEY",
        "ASSIGN_OP", "LE_OP", "LT_OP", "GE_OP", "GT_OP", "EQ_OP", "NE_OP", "ADD_OP", "SUB_OP", "MUL_OP", "DIV_OP", "END_KEY"};

        scanner scan = new scanner(tokens, tokensID);

        //arraylist to store all lines and token ids
        ArrayList<String> lines = new ArrayList<String>();

        System.out.println("\n\nBeginning Scanner...\n");

        //begin reading in lines
        File f = new File("Test1.jl");
        BufferedReader br = new BufferedReader(new FileReader(f));

        //string to store each line read from buffered reader
        String s;
        //int variable for start of function, used to indicate the function() method has been used
        int funcStart = 0;
        //count variable to count the number of lines in the file
        int count = 1;

        while((s = br.readLine()) != null){
            String str = s;
            //checks if the function has started
            if(scan.isSubstring("function", str)){
                funcStart++;
            }
            //if the function has started, begin lookup
            if(funcStart > 0){
                //print out line number
                System.out.println("Line: " + count + " ");
                //split string into tokens (omit spaces) and store in array splitStr
                String[] splitStr = str.trim().split("\\s+");   
                //go through each splitStr index and use lookup function to identify the tokens
                for(int i = 0; i < splitStr.length; i++){
                    String lineOut = scan.lookup(splitStr[i]);
                    //print the lookup output
                    System.out.println(lineOut);
                    //add lookup output to array list "lines"
                    lines.add(lineOut);
                }
                System.out.println();
            }
            //increase count variable for counting lines
            count++;
            
        }

        //close buffered reader
        br.close();

        System.out.println("...End Scanner\n\n\n\nBeginning Parser...\n");

        for(int i = 0; i < lines.size(); i++){
            String value = lines.get(i).substring(0, 4);
            int num = Integer.parseInt(value);

            System.out.println(num);
            /*switch(){
                case 
            }*/
        }

        
    }
}
