import java.io.*;

public class Scanner{
    public static String[] tokens = {"function", "if", "else", "while", "for", "print",
    "=","<=","<",">=",">","==","!=","+","-","*","/","%","\\","^", "end"};

    public static void main(String[] args) throws Exception{
        File f = new File("Test1.jl");
        BufferedReader br = new BufferedReader(new FileReader(f));

        String s;
        int funcStart = 0;
        int count = 1;

        while((s = br.readLine()) != null){
            System.out.println("Line: " + count);
            String str = s;
            if(isSubstring("function", str)){
                funcStart++;
            }
            if(funcStart > 0){
                String[] splitStr = str.trim().split("\\s+");   
                for(int i = 0; i < splitStr.length; i++){
                    System.out.println(splitStr[i]);
                    System.out.println(lookup(splitStr[i]));
                }
            }
            count++;
        }

        
        br.close();
    }

    public static int lookup(String name){
        for(int i = 0; i < tokens.length; i++){
            if(name.equals(tokens[i])){
                return i;
            }else if(isNumeric(name)){
                return 89;
            }
        }
        return -1;
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