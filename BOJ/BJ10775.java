import java.io.*;

// 공항

public class BJ10775 {
    
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        parent = new int[g + 1];
        for(int i = 1; i <= g; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for(int i = 1; i <= p; i++) {
            int x = Integer.parseInt(br.readLine());
            int px = findParent(x);
            if(px == 0) break;
            union(px, px - 1);
            ans++;
        }
        
        System.out.println(ans);
    }
    
    private static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if(pa == pb) return;
        parent[pa] = pb;
    }

    private static int findParent(int x) {
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

}