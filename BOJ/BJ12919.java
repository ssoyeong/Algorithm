import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// A와 B 2

public class BJ12919 {

    static String S, T;
    static boolean ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        backTracking(T.length());
        System.out.println(0);
    }

    private static void backTracking(int depth) {

        if(depth == S.length()) {
            if(S.equals(T)) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if(T.charAt(T.length() - 1) == 'A') {
            T = T.substring(0, T.length() - 1);
            backTracking(depth - 1);
            T = T.concat("A");
        }

        if(T.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(T);
            T = sb.reverse().toString();
            T = T.substring(0, T.length() - 1);
            backTracking(depth - 1);
            T = T.concat("B");
            sb = new StringBuilder(T);
            T = sb.reverse().toString();
        }
    }
     
}
