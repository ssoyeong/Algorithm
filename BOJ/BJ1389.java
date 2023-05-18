import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// 케빈 베이컨의 6단계 법칙

public class BJ1389 {
    
    private static class User implements Comparable<User> {
        int num;
        int total;

        User(int num, int total) {
            this.num = num;
            this.total = total;
        }

        @Override
        public int compareTo(User o) {
            if(this.total == o.total) return this.num - o.num;
            return this.total - o.total;
        }
    }
    static int n, m;
    static int[][] adj;
    static PriorityQueue<User> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                else adj[i][j] = 99999;
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    adj[i][j] = Integer.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 1; j <= n; j++) {
                sum += adj[i][j];
            }
            queue.add(new User(i, sum));
        }

        User poll = queue.poll();
        return poll.num;
    }
}
