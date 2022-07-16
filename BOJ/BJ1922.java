import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 네트워크 연결

public class BJ1922 {

    private static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int n, m;
    static int ans;
    static int[] parent;
    static PriorityQueue<Edge> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n+1];

        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            queue.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        while(!queue.isEmpty()) {
      
            unionFind(queue.poll());
        }

        System.out.println(ans);
    }

    private static void unionFind(Edge edge) {

        int pa = findParent(edge.a);
        int pb = findParent(edge.b);
        if(pa == pb) return;
        else {
            parent[pa] = pb;
            ans += edge.cost;
        }
    }

    private static int findParent(int x) {

        if(parent[x] == x) return x;
        else return parent[x] = findParent(parent[x]);
    }
    
}
