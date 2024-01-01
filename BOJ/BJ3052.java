import java.io.*;
import java.util.*;

// 나머지

public class BJ3052 {

    static final int TIME = 10;
    static final int DIVISOR = 42;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int i = 0;
        while (i < TIME) {
            int n = Integer.parseInt(br.readLine());
            set.add(n % DIVISOR);
            i++;
        }

        System.out.println(set.size());
    }

    
}
