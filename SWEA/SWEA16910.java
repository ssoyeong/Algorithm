import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 원 안의 점

public class SWEA16910 {

    static int t;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            int ans = solution();
            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {
        int cnt = 0;

        for(int i = 1; i <= n-1; i++) {
            for(int j = n-1; j >= 1; j--) {
                if(i*i + j*j <= n*n) {
                    cnt += j;
                    break;
                }
            }
        }

        cnt *= 4;
        cnt = cnt + n * 4 + 1;
        return cnt;
    }
    
}
