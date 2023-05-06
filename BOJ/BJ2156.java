import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 포도주 시식

public class BJ2156 {

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solution();
        System.out.println(dp[n-1]);
    }

    private static void solution() {

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                dp[i] = arr[i];
            }
            else if(i == 1) {
                dp[i] = arr[i-1] + arr[i];
            }
            else if(i == 2) {
                dp[i] = Integer.max(arr[i-2] + arr[i-1], Integer.max(arr[i-2], arr[i-1]) + arr[i]);
            }
            else {
                dp[i] = Integer.max(dp[i-1], Integer.max(dp[i-3] + arr[i-1], dp[i-2]) + arr[i]);
            }
        }
    }
    
}
