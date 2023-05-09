import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 거짓말

public class BJ1043 {

    static int n, m;
    static int trueParent;      // 진실을 아는 가장 부모 노드
    static int ans;
    static int[] parent;
    static int[] party;         // 각 파티의 대표 사람 번호

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        party = new int[m];
        ans = m;

        st = new StringTokenizer(br.readLine());
        int numOfTrue = Integer.parseInt(st.nextToken());
        for(int i = 0; i < numOfTrue; i++) {
            int x = Integer.parseInt(st.nextToken());
            if(i == 0) {
                trueParent = x;
            }
            else {
                parent[x] = trueParent;
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int numOfVisit = Integer.parseInt(st.nextToken());
            for(int j = 0; j < numOfVisit; j++) {
                int x = Integer.parseInt(st.nextToken());
                
                if(j == 0) {
                    party[i] = findParent(x);
                }
                else {
                    union(party[i], x);
                }
            }
        }

        for(int i = 0; i < m; i++) {
            int parent = findParent(party[i]);
            if(parent == trueParent) ans--;
        }

        System.out.println(ans);
    }

    private static void union(int a, int b) {

        a = findParent(a);
        b = findParent(b);

        if(a != b) {

            if(b == trueParent) {
                parent[a] = b;
            }
            else {
                parent[b] = a;
            }
        }
    }

    private static int findParent(int x) {

        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
    
}
