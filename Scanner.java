/*
 * Class:       CS 4308 Section 2
 * Term:        Spring 2022
 * Name:        Alexa Garcia
 * Instructor:   Sharon Perry
 * Project:     Deliverable P1 Scanner
 */


import java.io.*;
import java.nio.file.*;

public class Scanner{
    public static String[] tokens = {"function", "if", "else", "while", "for", "print",
    "=","<=","<",">=",">","==","~=","+","-","*","/", "end"};

    public static String[] tokensID = {"FUNC_KEY", "IF_KEY", "ELSE_KEY", "WHILE_KEY", "FOR_KEY", "PRINT_KEY",
    "ASSIGN_OP", "LE_OP", "LT_OP", "GE_OP", "GT_OP", "EQ_OP", "NE_OP", "ADD_OP", "SUB_OP", "MUL_OP", "DIV_OP", "END_KEY"};

    public static void main(String[] args) throws Exception{
        

        //get number of lines
        Path file = Paths.get("Test1.jl");
        try{
            long lines = Files.lines(file).count();
        }catch(Exception e){
            e.getStackTrace();
        }

        //begin reading in lines
        File f = new File("Test1.jl");
        BufferedReader br = new BufferedReader(new FileReader(f));

        String s;
        int funcStart = 0;
        int count = 1;

        while((s = br.readLine()) != null){
            System.out.println("Line: " + count + " ");
            String str = s;
            if(isSubstring("function", str)){
                funcStart++;
            }
            if(funcStart > 0){
                String[] splitStr = str.trim().split("\\s+");   
                for(int i = 0; i < splitStr.length; i++){
                    System.out.println(lookup(splitStr[i]));
                }
            }
            count++;
            System.out.println();
        }

        
        br.close();
    }

    public static String lookup(String name){
        for(int i = 0; i < tokens.length; i++){
            if(name.equals(tokens[i])){
                return (i + 5000) + " " + tokensID[i];
            }else if(isNumeric(name)){
                return 5018 + " LITERAL_INT";
            }else if(isSubstring("print", name)){
                return 5005 + " " + tokensID[5];
            }else if(name.matches("[A-Za-z]{1}")){
                return 5019 + " ID";
            }
        }
        return 5019 + " ID";
    }

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