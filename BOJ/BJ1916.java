import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 최소비용 구하기

public class BJ1916 {

    private static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int n, m;
    static int start, end;
    static int[] dp;
    static boolean[] visited;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        visited = new boolean[n+1];
        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, cost));  
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dp[start] = 0;

        if(start == end) dp[end] = 0;
        else dijkstra();

        System.out.println(dp[end]);
    }
    
    private static void dijkstra() {

        queue.add(new Node(start, 0));

        while(!queue.isEmpty()) {

            Node poll = queue.poll();

            if(poll.cost > dp[end]) return;
            if(visited[poll.to]) continue;
            visited[poll.to] = true;

            for(Node node : adj[poll.to]) {

                int newCost = node.cost + poll.cost;
                if(newCost < dp[node.to]) {
                    dp[node.to] = newCost;
                    queue.add(new Node(node.to, newCost));
                }
            }
        }
    }
}
