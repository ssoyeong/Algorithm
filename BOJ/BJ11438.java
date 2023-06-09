import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// LCA 2

public class BJ11438 {
    
    static int n, m;
    static int height;
    static int[] depth;
    static int[][] parent;              // parent[v][n] 노드 v의 2^n번째 조상 노드 번호
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        height = getMaxHeight();
        depth = new int[n+1];
        parent = new int[n+1][height + 1];
        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        init(1, 1);     // depth와 parent 초기화
        fillParents();

        m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            int ans = lca(u, v);
            sb.append(ans + "\n");
        }

        System.out.println(sb.toString());

    }

    private static int getMaxHeight() {
        // 트리의 최대 높이
        // 최대 2^h까지 자식 노드를 가질 수 있다고 가정하면, 밑이 2인 로그를 취해서 h값을 구함
        return (int) Math.ceil(Math.log(n) / Math.log(2));
    }

    private static void init(int id, int cnt) {
        // depth 값 초기화
        depth[id] = cnt;

        // id 노드의 자식 노드들 탐색
        for(int i = 0; i < adj[id].size(); i++) {
            int target = adj[id].get(i);

            if(depth[target] == 0) {        // 아직 미방문한 노드라면
                init(target, cnt + 1);
                parent[target][0] = id;     // 2^0번째 부모 값 초기화
            }
        }
    }

    private static void fillParents() {
        
        for(int i = 1; i < height; i++) {
            for(int j = 1; j <= n; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    private static int lca(int a, int b) {

        // depth[a] >= depth[b] 이도록 조정
        if(depth[a] < depth[b]) {       
            int temp = a;
            a = b;
            b = temp;
        }

        // 더 깊은 a를 depth[a] - depth[b] 의 차이에 가깝게 점프해서 depth를 맞추기
        for(int i = height - 1; i >= 0; i--) {       
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }

        // depth를 맞췄는데 같다면 종료
        if(a == b) return a;

        // depth가 같으므로 2^i번째로 점프하며 공통 조상이 달라지는 경우 찾기
        for(int i = height - 1; i >= 0; i--) {
            if(parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        // 달라진 경우의 바로 위 부모가 가장 가까운 공통 조상임
        return parent[a][0];
    }

}
