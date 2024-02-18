import java.io.*;
import java.util.*;

// 결혼식

public class BJ5567 {
    
    static int n;
    static int cnt;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;
    static ArrayDeque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(m > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
            m--;
        }

        visited[1] = true;
        // 상근(1번)의 친구
        for(int i = 0; i < adj[1].size(); i++) {
            visited[adj[1].get(i)] = true;
            queue.add(adj[1].get(i));
            cnt++;
        }

        // 상근(1번)의 친구의 친구
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            for(int i = 0; i < adj[poll].size(); i++) {
                if(!visited[adj[poll].get(i)]) {
                    visited[adj[poll].get(i)] = true;
                    cnt++;
                }

            }
        }

        System.out.println(cnt);
    }
}
