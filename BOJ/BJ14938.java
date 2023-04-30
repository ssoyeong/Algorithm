
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 서강그라운드


public class BJ14938 {

    static int n, m, r;
    static int ans;
    static int[] items;
    static int[][] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n+1];
        adj = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j) adj[i][j] = 99999;
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            adj[a][b] = l;
            adj[b][a] = l;
        }

        floydWarshall();
        getItems();
        System.out.println(ans);
    }

    private static void floydWarshall() {

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    adj[i][j] = Integer.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }
    }

    private static void getItems() {

        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 1; j <= n; j++) {
                if(adj[i][j] <= m) {
                    sum += items[j];
                }
            }
            ans = Integer.max(ans, sum);
        }
    }

    
}
