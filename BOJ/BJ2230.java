import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

// 수 고르기

public class BJ2230 {
    static int n, m;
    static int[] arr;
    static int minDiff = Integer.MAX_VALUE;
    static int start, end;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        start = 0;
        end = 0;

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solution();
        System.out.println(minDiff);
    }

    private static void solution() {

        Arrays.sort(arr);
        
        while(true) {

            if(start == n || end == n) break;

            int diff = arr[end] - arr[start];
            if(diff >= m) {
                minDiff = Integer.min(minDiff, diff);
                start++;
            }
            else {
                end++;
            }
        }
    }
}
