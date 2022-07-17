import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.PriorityQueue;

// 도시 분할 계획

public class BJ1647 {

    private static class Node implements Comparable<Node> {
        int u;
        int v;
        int cost;

        Node(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int n, m;
    static int answer;
    static int[] parent;
    static HashSet<Integer> set = new HashSet<>();      // parents를 담음
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
            set.add(i);
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            queue.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        solution();
        System.out.println(answer);
    }

    private static void solution() {

        while(!queue.isEmpty()) {

            if(set.size() == 2) break;
            
            Node poll = queue.poll();
            unionFind(poll);
            System.out.println("poll: " + poll.u + " " + poll.v + " " + poll.cost);
            System.out.println("set: " + set);
            System.out.println("answer: " + answer);
            System.out.println("==========");
        }
    }

    private static void unionFind(Node poll) {

        int pa = findParent(poll.u);
        int pb = findParent(poll.v);

        if(pa == pb) return;
        parent[pa] = pb;
        set.remove(pa);
        answer += poll.cost;
    }

    private static int findParent(int x) {

        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
    
}
