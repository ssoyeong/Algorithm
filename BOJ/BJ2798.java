import java.io.*;
import java.util.*;

// 블랙잭

public class BJ2798 {
    
    static int n, m;
    static int diffMin = Integer.MAX_VALUE;
    static int ans;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(0, 0, 0);
        System.out.println(ans);
    }

    private static void backTracking(int depth, int idx, int total) {

        if(total > m) return;

        if(depth == 3) {

            if(diffMin > Math.abs(total - m)) {
                diffMin = Math.abs(total - m);
                ans = total;
                
                if(diffMin == 0) {
                    System.out.println(ans);
                    System.exit(0);
                }
            }
            return;
        }

        for(int i = idx; i < n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            total += arr[i];

            backTracking(depth + 1, i + 1, total);

            visited[i] = false;
            total -= arr[i];
        }
    }
    
}
