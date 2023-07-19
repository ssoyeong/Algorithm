import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 사이클 게임

public class BJ20040 {

    static int n, m;
    static int[][] input;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        input = new int[m][2];
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {

        for(int i = 0; i < m; i++) {

            boolean isCycle = union(input[i][0], input[i][1]);
            if(isCycle) return i + 1;
        }
        return 0;
    }

    private static boolean union(int a, int b) {

        int pa = findParent(a);
        int pb = findParent(b);

        if(pa == pb) return true;
        else if(pa < pb) parent[pb] = pa;
        else parent[pa] = pb;

        return false;
    }

    private static int findParent(int x) {

        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }
}

