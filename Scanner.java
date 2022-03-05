import java.io.*;

public class Scanner{
    public String[] tokens = {"if", "while", "for", "print", "function",
    "=","<=","<",">=",">","==","!=","+","-","*","/","%","\\","^", "end"};

    public static void main(String[] args) throws Exception{
        File f = new File("Test2.jl");
        BufferedReader br = new BufferedReader(new FileReader(f));

        String s;

        while((s = br.readLine()) != null){
            System.out.println(s);
        }

        br.close();
    }

    public int lookup(String name){
        
        return 0;
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
}