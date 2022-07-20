import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 파티

public class BJ1238 {

    private static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int n, m, x;
    static boolean[] visited;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        visited = new boolean[n+1];
        ArrayList<Node>[] adj = new ArrayList[n+1];
        ArrayList<Node>[] adjReverse = new ArrayList[n+1];

        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            adjReverse[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, t));
            adjReverse[v].add(new Node(u, t));      // 전체->목적지 최단경로를 구하기 위해, 역방향으로 입력받아 목적지->전체 다익스트라를 사용
        }

        int[] cost = dijkstra(adj);
        int[] costReverse = dijkstra(adjReverse);

        int[] answer = new int[n+1];
        for(int i = 1; i <= n; i++) {
            answer[i] = cost[i] + costReverse[i];
        }
        Arrays.sort(answer);
        System.out.println(answer[n]);
    }

    private static int[] dijkstra(ArrayList<Node>[] list) {

        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        costs[x] = 0;
        queue.add(new Node(x, 0));

        while(!queue.isEmpty()) {
            Node poll = queue.poll();

            if(visited[poll.to]) continue;
            visited[poll.to] = true;

            for(int i = 0; i < list[poll.to].size(); i++) {
                Node target = list[poll.to].get(i);
                costs[target.to] = Math.min(costs[target.to], costs[poll.to] + target.weight);
                queue.add(new Node(target.to, costs[target.to]));
            }
        }
        
        return costs;        
    }
    
}
