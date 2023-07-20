import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 차이를 최대로

public class BJ10819 {
    
    static int n;
    static int max;
    static boolean[] visited;
    static int[] input;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        input = new int[n];
        arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        
        backTracking(0);
        System.out.println(max);
    }
    
    private static void backTracking(int depth) {
        if(depth == n) {
            
            int total = 0;
            for(int i = 0; i < n - 1; i++) {
                total += Math.abs(input[arr[i]] - input[arr[i + 1]]);
            }
            max = Integer.max(max, total);
        }
        
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
                
            visited[i] = true;
            arr[depth] = i;
            backTracking(depth + 1);
            visited[i] = false;
        }
    }
}