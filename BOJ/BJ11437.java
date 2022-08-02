import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// LCA

public class BJ11437 {

    static int n, m;
    static boolean[] visited;
    static int[] parent;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        parent = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 1; i < n; i++) {
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        setEachParent();

        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = solution(a, b);
            sb.append(lca).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void setEachParent() {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()) {
            int poll = queue.poll();
            for(int i = 0; i < adj[poll].size(); i++) {
                int x = adj[poll].get(i);
                if(visited[x]) continue;
                parent[x] = poll;
                queue.add(x);
                visited[x] = true;
            }
        }
    }
    
    private static int solution(int a, int b) {

        Arrays.fill(visited, false);
        setAncestorsTrue(a);
        return findCommon(b);
    }

    private static void setAncestorsTrue(int x) {
        
        visited[x] = true;
        if(x == 1) return;
        setAncestorsTrue(parent[x]);
    }

    private static int findCommon(int x) {

        while(true) {
            if(visited[x]) return x;
            x = parent[x];
        }
    }
}
