import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 분수 합

public class BJ1735 {

    static int n1, m1;
    static int n2, m2;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        
        int n = n1 * m2 + n2 * m1;
        int m = m1 * m2;

        int common = n > m ? gcd(n, m) : gcd(m, n);
        n /= common;
        m /= common;
        System.out.println(n + " " + m);
        
    }

    static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    
}
