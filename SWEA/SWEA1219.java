import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

// [S/W 문제해결 기본] 4일차 - 길찾기

public class SWEA1219 {

    static int tc;
    static int n;
    static int isValid;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {

            isValid = 0;

            String line = br.readLine();
            if(line == null) break;
            st = new StringTokenizer(line);
            tc = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            adj = new ArrayList[100];
            for(int i = 0; i < 100; i++) {
                adj[i] = new ArrayList<>();
            }

            visited = new boolean[100];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a].add(b);
            }
            
            solution();
            sb.append("#" + tc + " " + isValid + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void solution() {

        for(int i = 0; i < adj[0].size(); i++) {
            dfs(adj[0].get(i));
            if(isValid == 1) return;
        }
    }

    private static void dfs(int start) {

        if(start == 99) {
            isValid = 1;
            return;
        }

        for(int i = 0; i < adj[start].size(); i++) {
            int target = adj[start].get(i);
            if(visited[target]) continue;
            visited[target] = true;
            dfs(target);
        }
    }
    
}
