import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 최소 덧셈

public class SWEA17299 {

    static int T;
    static String S;
    static String A, B;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            S = br.readLine();
            int ans = solution();
            sb.append("#" + tc + " " + ans + "\n");
        }
        
        System.out.println(sb.toString());
    }

    private static int solution() {

        int min = Integer.MAX_VALUE;
        A = "";
        B = S;

        for(int i = 0; i < S.length()-1; i++) {

            A = A.concat(B.substring(0, 1));
            B = B.substring(1, B.length());
            int result = Integer.parseInt(A) + Integer.parseInt(B);
            min = Integer.min(min, result);
        }

        return min;
    }
}
