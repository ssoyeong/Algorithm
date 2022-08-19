import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// N과 M (2)

public class BJ15650 {

    static int n, m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        
        backTracking(0, 0);
        System.out.println(sb.toString());
    }

    private static void backTracking(int level, int idx) {

        if(level == m) {
            
            for(int i = 0; i < m; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = idx+1; i <= n; i++) {
            arr[level] = i;
            backTracking(level+1, i);
        }
    }
    
}
