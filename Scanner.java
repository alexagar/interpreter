import java.io.*;
import java.net.*;


public class Scanner{
    public static void main(String[] args) throws Exception{
        File f = new File("Test2.jl");
        BufferedReader br = new BufferedReader(new FileReader(f));

        String s;

        while((s = br.readLine()) != null){
            System.out.println(s);
        }
    }
}