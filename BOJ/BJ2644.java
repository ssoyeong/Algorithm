import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 촌수계산

public class BJ2644 {
    
    private static class Node {
        int num;
        int cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int n;
    static int n1, n2;
    static int m;
    static int ans;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());
        
        m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        solution();
        System.out.println(ans);
    }

    private static void solution() {

        visited[n1] = true;
        queue.add(new Node(n1, 0));

        while(!queue.isEmpty()) {

            Node poll = queue.poll();

            for(int i = 0; i < adj[poll.num].size(); i++) {
                int target = adj[poll.num].get(i);

                if(visited[target]) continue;

                visited[target] = true;
                queue.add(new Node(target, poll.cnt + 1));

                if(target == n2) {
                    ans = poll.cnt + 1;
                    return;
                }
            }
        }
        
        ans = -1;
    }
}
