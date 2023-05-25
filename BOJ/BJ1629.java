import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 곱셈

public class BJ1629 {

    static int a, b, c;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        long ans = solution(b);
        System.out.println(ans);
    }

    private static long solution(int expo) {

        if(expo == 1) return a % c;
        
        long temp = solution(expo/2);

        if(expo % 2 == 0) return temp * temp % c;
        return (temp * temp % c) * a % c;
    }
}
