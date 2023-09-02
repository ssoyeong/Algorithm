import java.io.*;
import java.util.*;

// 운동

public class BJ1956 {
    
    static final int INF = 99999999;
    static int v;
    static int[][] adj;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        adj = new int[v][v];

        for(int i = 0; i < v; i++) {
            for(int j = 0; j < v; j++) {
                if(i == j) adj[i][j] = 0;
                else adj[i][j] = INF;
            }
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            adj[a][b] = c;
        }

        for(int k = 0; k < v; k++) {
            for(int i = 0; i < v; i++) {
                for(int j = 0; j < v; j++) {
                    adj[i][j] = Integer.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        int ans = INF;
        for(int i = 0; i < v - 1; i++) {
            for(int j = i + 1; j < v; j++) {
                ans = Integer.min(ans, adj[i][j] + adj[j][i]);
            }
        }
        System.out.println(ans >= INF ? -1 : ans);
    }
}
