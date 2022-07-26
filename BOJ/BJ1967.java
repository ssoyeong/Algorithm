import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 트리의 지름

public class BJ1967 {

    private static class Node {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
    static int n;
    static int answer;
    static int maxNode;
    static boolean[] visited;
    static ArrayList<Node>[] adj;
   
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if(n == 1) {    // 루트만 존재할 경우 종료
            System.out.println(0);
            return;
        }

        visited = new boolean[n+1];
        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, c));
            adj[v].add(new Node(u, c));
        }

        visited[1] = true;
        dfs(1, 0);      // 루트로부터 최대 가중치를 갖는 leaf를 maxNode에 저장
       
        Arrays.fill(visited, false);
        visited[maxNode] = true;
        dfs(maxNode, 0);    // maxNode를 root로 삼아 한 번 더 bfs 탐색

        System.out.println(answer);
    }
    
    static private void dfs(int node, int total) {

        for(int i = 0; i < adj[node].size(); i++) {
            if(visited[adj[node].get(i).vertex]) continue;

            total += adj[node].get(i).cost;
            visited[adj[node].get(i).vertex] = true;
            dfs(adj[node].get(i).vertex, total);
            total -= adj[node].get(i).cost;
            visited[adj[node].get(i).vertex] = false;
        }

        if(answer < total) {
            answer = total;
            maxNode = node;
        }

    }
}
