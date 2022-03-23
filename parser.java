/*
 * Class:       CS 4308 Section 2
 * Term:        Spring 2022
 * Name:        Alexa Garcia
 * Instructor:   Sharon Perry
 * Project:     Deliverable P2 Parser
 */

import java.util.ArrayList;

public class parser {
    
    private ArrayList<String> lines = new ArrayList<String>();

    public parser(ArrayList<String> lines){
        this.lines = lines;
    }

    public void start_parse(){
        for(int i = 0; i < lines.size(); i++){
            String value = lines.get(i).substring(0, 4);
            int num = Integer.parseInt(value);

            System.out.println(num);
            switch(num){
                
            }
        }
    }

    public void program(){
        
    }

    public void block(){

    }

    public void statement(){

    }

    public void if_statement(){

    }

    public void while_statement(){

    }

    public void assignment_statement(){

    }

    public void for_statement(){

    }

    public void print_statement(){

    }

    public void iter(){

    }

    public void boolean_expression(){

    }

    public void relative_op(){

    }

    public void arithmetic_expression(){

    }

    public void binary_expression(){

    }

    public void arithmetic_op(){

    }
}
