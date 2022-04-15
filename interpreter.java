
import java.util.ArrayList;

public class interpreter {

    private ArrayList<String> lines = new ArrayList<String>();      //scanner output
    private ArrayList<String> parserOut = new ArrayList<String>();  //parser output
    private ArrayList<String> parserTokens = new ArrayList<String>();

    public interpreter(ArrayList<String> lines, ArrayList<String> parserOut){
        this.lines = lines;
        this.parserOut = parserOut;
    }

    public void start_interpret(){
        int tokens[] = new int[lines.size()];

        //takes in first 4 digits of every line of ArrayList "lines" and creates an int array from it called "tokens"
        for(int i = 0; i < lines.size(); i++){
            String value = lines.get(i).substring(0, 4);
            int num = Integer.parseInt(value);

            tokens[i] = num;
        }

        reader();
    }

    public void reader(){
        for(int i = 0; i < parserOut.size(); i++){
            String[] splitStr = parserOut.get(i).trim().split("\\s+");
        }

        for(int i = ){

        }
    }

    
}
