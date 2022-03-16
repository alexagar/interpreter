/*
 * Class:       CS 4308 Section 2
 * Term:        Spring 2022
 * Name:        Alexa Garcia
 * Instructor:   Sharon Perry
 * Project:     Deliverable P2 Scanner
 */

public class scanner{
    private String[] tokens;
    private String[] tokensID;

    scanner(String[] tokens, String[] tokensID){
        this.tokens = tokens;
        this.tokensID = tokensID;
    }

    public String lookup(String name){
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