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

public class parser {
    
    private ArrayList<String> lines = new ArrayList<String>();
    private ArrayList<String> parserOut = new ArrayList<String>();
    private int start = 0;
    private int end = 0;

    //constructor to initialize lines
    public parser(ArrayList<String> lines){
        this.lines = lines;
    }

    //function to start parse algorithm
    public void start_parse(){
        System.out.println("\n\nBeginning Parser...\n");

        int[] tokens = new int[lines.size()];
        
        //takes in first 4 digits of every line of ArrayList "lines" and creates an int array from it called "tokens"
        for(int i = 0; i < lines.size(); i++){
            String value = lines.get(i).substring(0, 4);
            int num = Integer.parseInt(value);

            tokens[i] = num;
        }

        //set end index value to be the last index of tokens
        end = tokens.length;

        //run program with input tokens to start the parse algorithm
        program(tokens);

        //print out every line of parserOut
        for(int i = 0; i < parserOut.size(); i++){
            System.out.println(parserOut.get(i));
        }

        //print statement to signify end of parser algorithm
        System.out.println("\n...End Parser");
    }

    //check program grammar
    public void program(int[] tokens){
        if(tokens[0] == 5000){
            if(tokens[1] == 5020){
                if(tokens[2] == 5021){
                    if(tokens [3] == 5022){
                        if(tokens[tokens.length-1] == 5017){
                            parserOut.add("<program> -> function id () <block> end");
                            start = 4;
                            block(tokens);
                        }else{
                            parserOut.add("Error: Expected 'end' for 'function()'");
                        }
                    }else{
                        parserOut.add("Error: Expected ')' after ID");
                    }
                }else{
                    parserOut.add("Error: Expected '(' after ID");
                }
            }else{
                parserOut.add("Error: Expected identifier after 'function()'");
            }
        }else{
            parserOut.add("Error: Expected 'function' keyword.");
        }
    }

    //check block grammar
    public void block(int[] tokens){
        parserOut.add(block_out(tokens));
        for(int i = start; i < end; i++){
            statement(tokens);
        }
    }

    //method for printing block grammar (counts statements present)
    public String block_out(int[] tokens){
        String out = "<block> ->";
        for(int i = start; i < end; i++){
            if(tokens[i] == 5001){
                out += " <statement>";
            }else if(tokens[i] == 5020 && tokens[i+1] == 5006){
                out += " <statement>";
            }else if(tokens[i] == 5003){
                out += " <statement>";
            }else if(tokens[i] == 5004){
                out += " <statement>";
            }else if(tokens[i] == 5005){
                out += " <statement>";
            }
        }
        end = tokens.length;
        return out;
    } 

    //method that checks for a statement
    public void statement(int[] tokens){
        if(tokens[start] == 5001){
            start++;
            parserOut.add("<statement> -> <if_statement>");
            if_statement(tokens);
        }else if(tokens[start] == 5020){
            start++;
            parserOut.add("<statement> -> <assignment_statement>");
            assignment_statement(tokens);
        }else if(tokens[start] == 5003){
            start++;
            parserOut.add("<statement> -> <while_statement>");
            while_statement(tokens);
        }else if(tokens[start] == 5004){
            start++;
            parserOut.add("<statement> -> <repeat_statement>");
            repeat_statement(tokens);
        }else if(tokens[start] == 5005){
            start++;
            parserOut.add("<statement> -> <print_statement>");
            print_statement(tokens);
        }
    } 

    //check if statement grammar, print error if not correct, print if statement out if it is correct
    public void if_statement(int[] tokens){
        int index = parserOut.size();
        //check for boolean
        if(check_boolean(tokens)){
            boolean_expression(tokens);
            //check for then key
            if(tokens[start+3] == 5018){
                start += 4;
                end = getIndexOf(start, 5002, tokens);
                //get block
                block(tokens);
                //check for else key
                if(tokens[start] == 5002){
                    start++;
                    end = getIndexOf(start, 5017, tokens);
                    //do block
                    block(tokens);
                    //check for end key
                    if(tokens[start] == 5017){
                        //add line to parserOut
                        parserOut.add(index,"<if_statement> -> if <boolean_expression> then <block> else <block> end");
                        start++;
                    }else{
                        parserOut.add("Error: expected 'end' after <block>");
                    }
                }else{
                    parserOut.add("Error: expected 'else' after <block>");
                }
            }else{
                parserOut.add("Error: expected then after <boolean_expression>");
            }
        }else{
            parserOut.add("Error: expected <boolean_expression> after if");
        }
    }

    //check assignment statement grammar
    public void assignment_statement(int[] tokens){
        //check for = sign
        if(tokens[start] == 5006){
            //check for arithmetic expression
            if(check_arithmetic_expression(tokens[start +1])){
                //add lines to parserOut
                parserOut.add("<assignment_statement> -> id <assignment_operator> <arithmetic_expression>");
                parserOut.add("<arithmetic_expression> -> <" + arithmetic_expression(tokens[start +1]) + ">");
                start += 2;
            }else{
                parserOut.add("Error: expected <arithmetic_expression> after <assignment_operator>");
            }
        }else{
            parserOut.add("Error: expected <assignment operator> after id");
        }
    }

    //check while statement grammar
    public void while_statement(int[] tokens){
        int index = parserOut.size();
        if(check_boolean(tokens)){
            boolean_expression(tokens);
            if(tokens[start+3] == 5023){
                start += 3;
                end = getIndexOf(start, 5017, tokens);
                block(tokens);
                if(tokens[start] == 5017){
                    parserOut.add(index, "<while_statement> -> while <boolean_expression> do <block> end");
                }
            }else{
                parserOut.add("Error: expected 'do' after <boolean_expression>");
            }
        }else{
            parserOut.add("Error: expected <boolean_expression> after while");
        }
    }

    //check repeat statement grammar
    public void repeat_statement(int[] tokens){
        int index = parserOut.size();
        end = getIndexOf(start, 5024, tokens);
        block(tokens);
        if(tokens[start] == 5024){
            start++;
            if(check_boolean(tokens)){
                parserOut.add(index, "<repeat_statement> -> repeat <block> until <boolean expression>");
                boolean_expression(tokens);
            }else{
                parserOut.add("Error: expected <boolean_expression> after until");
            }
        }else{
            parserOut.add("Error: expected 'until' after <block>");
        }
    }

    //check print grammar
    public void print_statement(int[] tokens){
        //check for open parenthesis
        if(tokens[start] == 5021){
            //check for arithmetic expression
            if(check_arithmetic_expression(tokens[start + 1])){
                //check for 
                if(tokens[start + 2] == 5022){
                    parserOut.add("<print statement> -> print ( <arithmetic_expression> )");
                    parserOut.add("<arithmetic_expression> -> <" + arithmetic_expression(tokens[start + 1]) + ">");
                    start += 3;
                }else{
                    parserOut.add("Error: expected ')' after <arithmetic_expression>");
                }
            }else{
                parserOut.add("Error: expected <arithmetic_expression> after '('");
            }
        }else{
            parserOut.add("Error: expected '(' after 'print'");
        }
    }

    //check if a boolean expression is present
    public boolean check_boolean(int[] tokens){
        if(check_relative_op(tokens[start])){
            if(check_arithmetic_expression(tokens[start+1])){
                if(check_arithmetic_expression(tokens[start+2])){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    //check boolean expression grammar
    public void boolean_expression(int[] tokens){
        if(check_relative_op(tokens[start])){
            if(check_arithmetic_expression(tokens[start+1])){
                if(check_arithmetic_expression(tokens[start+2])){
                    parserOut.add("<boolean_expression> -> <relative_op> <arithmetic_expression> <arithmetic_expression>");
                    parserOut.add("<relative_op> -> <" + relative_op(tokens[start]) + ">");
                    parserOut.add("<arithmetic_expression> -> <" + arithmetic_expression(tokens[start+1]) + ">");
                    parserOut.add("<arithmetic_expression> -> <" + arithmetic_expression(tokens[start+2]) + ">");
                }else{
                    parserOut.add("Error: expected <arithmetic_expression> after <relative_op>");
                }
            }else{
                parserOut.add("Error: expected <arithmetic_expression> after <relative_op>");
            }
        }else{
            parserOut.add("Error: expected <relative_op> for boolean expression");
        }
    }

    //checks if a relative op is present in the give token, if not return false
    public boolean check_relative_op(int token){
        if(token == 5007 || token == 5008 || token == 5009 || token == 5010 || token == 5011 || token == 5012){
            return true;
        }else{
            return false;
        }
    }

    //returns relative operator or error if unrecognized token
    public String relative_op(int token){
        switch(token){
            case 5007:
                return "le_operator";
            case 5008:
                return "lt_operator";
            case 5009:
                return "ge_operator";
            case 5010:
                return "gt_operator";
            case 5011:
                return "eq_operator";
            case 5012:
                return "ne_operator";
        }
        return "Error: expected relative_op is not present or defined incorrectly";
    }

    //checks if an arithmetic expression is present, if not, returns false
    public boolean check_arithmetic_expression(int token){
        if (token == 5020 || token == 5019 || check_arithmetic_op(token)){
            return true;
        }else{
            return false;
        }
    }

    //returns the arithmetic expression present in the parameter, otherwise returns Error
    public String arithmetic_expression(int token){
        switch(token){
            case 5020:
                return "id";
            case 5019:
                return "literal integer";
        }

        if(check_arithmetic_op(token)){
            return arithmetic_op(token);
        }
        return "Error: expected arithemetic_expression is not present or defined incorrectly";
    }

    //checks if an arithmetic_op is present, if not, returns false
    public boolean check_arithmetic_op(int token){
        if(token == 5013 || token == 5014 || token == 5015 || token == 5016){
            return true;
        }else{
            return false;
        }
    }

    //returns the arithmetic_op present in given parameter or returns Error if not found
    public String arithmetic_op(int token){
        switch(token){
            case 5013:
                return "add_operator";
            case 5014:
                return "sub_operator";
            case 5015:
                return "mul_operator";
            case 5016:
                return "div_operator";
        }
        return "Error: expected arithmetic_op is not present or defined incorrectly";
    }

    //gets index of the next integer that equals parameter "tokenFind"
    public int getIndexOf(int indexStart, int tokenFind, int[] tokens){
        boolean found = false;
        while(!found){
            if(tokens[indexStart] == tokenFind){
                found = true;
                return indexStart;
            }
            indexStart++;
        }
        return 0;
    }
}
