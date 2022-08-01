import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 트리의 지름

public class BJ1167 {

    private static class Node {
        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static int v;
    static int answer;
    static int maxNode;
    static boolean[] visited;
    static ArrayList<Node>[] adj;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());
        visited = new boolean[v+1];
        adj = new ArrayList[v+1];
        for(int i = 1; i <= v; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 1; i <= v; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            while(true) {
                int u = Integer.parseInt(st.nextToken());
                if(u == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                adj[v].add(new Node(u, cost));
            }
        }
        
        visited[1] = true;
        dfs(1, 0);
  
        answer = 0;
        Arrays.fill(visited, false);
        visited[maxNode] = true;
        dfs(maxNode, 0);

        System.out.println(answer);
    }

    private static void dfs(int x, int total) {

        for(int i = 0; i < adj[x].size(); i++) {
            int target = adj[x].get(i).num;
            if(visited[target]) continue;

            total += adj[x].get(i).cost;
            visited[target] = true;
            dfs(target, total);
            total -= adj[x].get(i).cost; 
        }

        if(answer < total) {
            answer = total;
            maxNode = x;
        }
    }
    
}
