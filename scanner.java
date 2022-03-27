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
import java.io.*;

public class scanner{
    private String[] tokens;                                    //array for tokens
    private String[] tokensID;         
    private String file_name;                         //array for tokenIDs
    private ArrayList<String> lines = new ArrayList<String>();  //arraylist to store all lines and token ids

    //constructor method to set lookup arrays
    scanner(String[] tokens, String[] tokensID, String file_name){
        this.tokens = tokens;
        this.tokensID = tokensID;
        this.file_name = file_name;
    }

    public void start_scan() throws Exception{
        System.out.println("\n\n\n\nBeginning Scanner...\n");

        //begin reading in lines
        File f = new File(file_name);
        BufferedReader br = new BufferedReader(new FileReader(f));

        //string to store each line read from buffered reader
        String s;
        //int variable for start of function, used to indicate the function() method has been used
        int funcStart = 0;
        //count variable to count the number of lines in the file

        while((s = br.readLine()) != null){
            String str = s;
            //checks if the function has started
            if(isSubstring("function", str)){
                funcStart++;
            }
            //if the function has started, begin lookup
            if(funcStart > 0){
                //split string into tokens (omit spaces) and store in array splitStr
                String[] splitStr = str.trim().split("\\s+");   
                //go through each splitStr index and use lookup function to identify the tokens
                for(int i = 0; i < splitStr.length; i++){
                    String lineOut = lookup(splitStr[i]);
                    //add lookup output to array list "lines"
                    lines.add(lineOut);
                }
            }
            //increase count variable for counting lines
        }

        for(int i = 0; i < lines.size(); i++){
            System.out.println(lines.get(i));
        }

        //close buffered reader
        br.close();

        System.out.println("...End Scanner");
    }
    //return 
    public ArrayList<String> getLines(){
        return lines;
    }

    //get token number
    public String lookup(String name){
        for(int i = 0; i < tokens.length; i++){
            //uses .equals to check if the string matches any token array entry
            if(name.equals(tokens[i])){
                return (i + 5000) + " " + tokensID[i];
            //uses isNumeric to check if the string is a number
            }else if(isNumeric(name)){
                return 5019 + " LITERAL_INT " + name;
            //uses isSubstring to check if the string is a print function
            }else if(isSubstring("print", name)){
                lines.add(5005 + " " + tokensID[5]);

                if(name.charAt(5) == '('){
                    lines.add(5021 + " " + tokensID[21]);
                }
                lines.add(lookup(name.substring(6, name.length()-1)));
                if(name.charAt(name.length()-1) == ')'){
                    return 5022 + " " + tokensID[22];
                }
            //uses .matches to check if the string is a letter
            }else if(name.matches("[A-Za-z]{1}")){
                return 5020 + " ID " + name;
            //check for function identifier in the format 'a()' (or any other letter)
            }else if(name.substring(0,1).matches("[A-Za-z]{1}") && name.charAt(1) == '(' && name.charAt(2) == ')'){
                lines.add(5020 + " ID " + name.charAt(0));
                lines.add(5021 + " " + tokensID[21]);
                return 5022 + " " + tokensID[22];
            }
        }
        //if not any of the above cases, it must be a function id so return this for function ids
        return "0000 UNRECOGNIZED TOKEN";
    }

    //Checks if the input string is a number and returns true if yes and false if no
    public boolean isNumeric(String strNum) {
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
    public boolean isSubstring(String s1, String s2){
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
