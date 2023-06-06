import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 특정한 최단 경로

public class BJ1504 {

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
    static int n, e;
    static int v1, v2;
    static int distV1toV2, dist1toV1, dist1toV2, distV1toN, distV2toN;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    static final int INF = 999999999;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        dist = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        visited = new boolean[n+1];

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        
        int ans = solution();
        if(ans >= INF || ans < 0) ans = -1;
        System.out.println(ans);
    }
    
    private static int solution() {
        
        dijkstra(1);
        dijkstra(v1);
        dijkstra(v2);
        
        if(dist1toV1 + distV2toN > dist1toV2 + distV1toN) {
            return dist1toV2 + distV1toN + distV1toV2;
        }
        return dist1toV1 + distV2toN + distV1toV2;
    }

    private static void dijkstra(int start) {

        Arrays.fill(dist, INF);
        dist[start] = 0;
        Arrays.fill(visited, false);
        queue.clear();

        queue.add(new Node(start, dist[start]));
        while(!queue.isEmpty()) {

            Node poll = queue.poll();

            if(visited[poll.to]) continue;
            visited[poll.to] = true;

            for(Node node : adj[poll.to]) {

                int newCost = poll.cost + node.cost;
                if(newCost < dist[node.to]) {
                    dist[node.to] = newCost;
                    queue.add(new Node(node.to, newCost));
                }
            }
        }

        if(start == 1) {
            dist1toV1 = dist[v1];
            dist1toV2 = dist[v2];
        }
        if(start == v1) {
            distV1toN = dist[n];
            distV1toV2 = dist[v2];
        }
        if(start == v2) {
            distV2toN = dist[n];
        }
    }
    
}
