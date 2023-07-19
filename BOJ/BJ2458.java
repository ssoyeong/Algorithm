import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 키 순서

public class BJ2458 {

    static int n, m;
    static boolean[][] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new boolean[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) adj[i][j] = true;
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a][b] = true;
        }

        int ans = solution();
        print();
        System.out.println(ans);

    }

    private static int solution() {

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(adj[i][j]) continue;
                    if(adj[i][k] && adj[k][j]) adj[i][j] = true;
                }
            }
        }

        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            boolean flag = true;
            for(int j = 1; j <= n; j++) {
                if(adj[i][j] || adj[j][i]) continue;
                else {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }

        return cnt;
    }

    private static void print() {

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(adj[i][j]) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
    }
}
