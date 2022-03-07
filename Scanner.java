/*
 * Class:       CS 4308 Section 2
 * Term:        Spring 2022
 * Name:        Alexa Garcia
 * Instructor:   Sharon Perry
 * Project:     Deliverable P1 Scanner
 */


import java.io.*;

import java.util.ArrayList;

public class Scanner{

    //token table array
    public static String[] tokens = {"function", "if", "else", "while", "for", "print",
    "=","<=","<",">=",">","==","~=","+","-","*","/", "end"};

    //token descriptor table array
    public static String[] tokensID = {"FUNC_KEY", "IF_KEY", "ELSE_KEY", "WHILE_KEY", "FOR_KEY", "PRINT_KEY",
    "ASSIGN_OP", "LE_OP", "LT_OP", "GE_OP", "GT_OP", "EQ_OP", "NE_OP", "ADD_OP", "SUB_OP", "MUL_OP", "DIV_OP", "END_KEY"};

    public static void main(String[] args) throws Exception{
        //begin reading in lines
        File f = new File("Test1.jl");
        BufferedReader br = new BufferedReader(new FileReader(f));

        //arraylist to store all lines and token ids
        ArrayList<String> lines = new ArrayList<String>();

        //string to store each line read from buffered reader
        String s;
        //int variable for start of function, used to indicate the function() method has been used
        int funcStart = 0;
        //count variable to count the number of lines in the file
        int count = 1;

        while((s = br.readLine()) != null){
            String str = s;
            //checks if the function has started
            if(isSubstring("function", str)){
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
                    String lineOut = lookup(splitStr[i]);
                    //print the lookup output
                    System.out.println(lineOut);
                    //add lookup output to array list "lines"
                    lines.add(lineOut);
                }
            }
            //increase count variable for counting lines
            count++;
            System.out.println();
        }

        //close buffered reader
        br.close();
    }

    public static String lookup(String name){
        for(int i = 0; i < tokens.length; i++){
            //uses .equals to check if the string matches any token array entry
            if(name.equals(tokens[i])){
                return (i + 5000) + " " + tokensID[i];
            //uses isNumeric to check if the string is a number
            }else if(isNumeric(name)){
                return 5018 + " LITERAL_INT " + name;
            //uses isSubstring to check if the string is a print function
            }else if(isSubstring("print", name)){
                return 5005 + " " + tokensID[5];
            //uses .matches to check if the string is a letter
            }else if(name.matches("[A-Za-z]{1}")){
                return 5019 + " ID " + name;
            }
        }
        //if not any of the above cases, it must be a function id so return this for function ids
        return 5019 + " ID " + name.charAt(0);
    }

    //Checks if the input string is a number and returns true if yes and false if no
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //Checks to see if string s1 is a substring of string s2 and returns true if yes and false if no
    static boolean isSubstring(String s1, String s2){
        int M = s1.length();
        int N = s2.length();
 
        for (int i = 0; i <= N - M; i++) {
            int j;
 
            for (j = 0; j < M; j++)
                if (s2.charAt(i + j)
                    != s1.charAt(j))
                    break;
 
            if (j == M)
                return true;
        }
 
        return false;
    }
}