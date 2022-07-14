import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// A와 B

public class BJ12904 {

    static String S;
    static String T;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        while(T.length() > S.length()) {

            if(T.charAt(T.length()-1) == 'B') {
                T = T.substring(0, T.length()-1);
                StringBuffer sb = new StringBuffer(T);
                T = sb.reverse().toString();
            }
            else T = T.substring(0, T.length()-1);
        }
        
        if(T.equals(S)) System.out.println(1);
        else System.out.println(0);

    }
    
}
