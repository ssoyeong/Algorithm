import java.io.*;
import java.util.*;

// 회장뽑기

public class BJ2660 {
    
    static class Node implements Comparable<Node> {
        int num;
        int value;

        Node(int num, int value) {
            this.num = num;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if(this.value == o.value) return this.num - o.num;
            return this.value - o.value;
        }
    }
    static int n;
    static int[][] dist;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dist = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                dist[i][j] = 50;
            }
        }

        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(u == -1 && v == -1) break;
            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        solution();
    }

    private static void solution() {

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            int max = 0;
            for(int j = 1; j <= n; j++) {
                max = Integer.max(max, dist[i][j]);
            }
            queue.add(new Node(i, max));
        }

        StringBuilder sb = new StringBuilder();
        int score = queue.peek().value;
        int cnt = 0;
        while(!queue.isEmpty()) {
            Node poll = queue.poll();
            if(poll.value == score) sb.append(poll.num + " ");
            else break;
            cnt++;
        }

        System.out.println(score + " " + cnt);
        System.out.println(sb.toString());
    }
}
