import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

// 창용 마을 무리의 개수
 
public class SWEA7465 {
     
    static int t;
    static int n, m;
    static int[] parent;
    static HashSet<Integer> group = new HashSet<>();
     
    public static void main(String[] args) throws IOException {
     
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
         
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= t; tc++) {
             
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
             
           parent = new int[n + 1];
            for(int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            group.clear();
             
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                union(u, v);
            }
             
            int ans = findGroup();
            sb.append("#" + tc + " " + ans + "\n");
        }
         
        System.out.println(sb.toString());
    }
     
    private static void union(int a, int b) {
         
        int pa = findParent(a);
        int pb = findParent(b);
         
        if(pa < pb) parent[pb] = pa;
        else if(pa > pb) parent[pa] = pb;
         
    }
     
    private static int findParent(int x) {
         
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }
     
    private static int findGroup() {
         
        for(int i = 1; i <= n; i++) {
            parent[i] = findParent(parent[i]);
        }
         
        for(int i = 1; i <= n; i++) {    
            if(!group.contains(parent[i])) {
                group.add(parent[i]);
            }
        }
         
        return group.size();
    }
     
}