import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 여러분의 다리가 되어 드리겠습니다!

public class BJ17352 {

    static int n;
    static int selectedFirstBridge;
    static int selectedSecondBridge;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i = 0; i < n - 2; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(parent[u], parent[v]);
        }

        selectUnconnectedBridges();
        System.out.println(selectedFirstBridge + " " + selectedSecondBridge);
    }

    private static void union(int a, int b) {

        int pa = findParent(a);
        int pb = findParent(b);
        parent[pa] = pb;
    }

    private static int findParent(int x) {

        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    private static void selectUnconnectedBridges() {

        selectedFirstBridge = findParent(parent[1]);

        for(int i = 2; i <= n; i++) {
            if(findParent(parent[i]) != selectedFirstBridge) {
                selectedSecondBridge = parent[i];
                return;
            }
        }
    }
    
}
