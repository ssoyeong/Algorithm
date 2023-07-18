import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

// 선수과목 (Prerequisite)

public class BJ14567 {

    static int n, m;
    static int[] ans;
    static int[] numOfParents;
    static ArrayList<Integer>[] adj;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        ans = new int[n + 1];
        numOfParents = new int[n + 1];
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            numOfParents[b]++;
            adj[a].add(b);
        }

        solution();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(ans[i] + " ");
        }
        System.out.println(sb.toString());
    }

    private static void solution() {

        for(int i = 1; i <= n; i++) {
            if(numOfParents[i] == 0) {
                queue.add(i);
                ans[i] = 1;
            }
        }

        while(!queue.isEmpty()) {

            int poll = queue.poll();

            for(int i = 0; i < adj[poll].size(); i++) {
                int target = adj[poll].get(i);

                numOfParents[target]--;
                if(numOfParents[target] == 0) {
                    queue.add(target);
                    ans[target] = ans[poll] + 1;
                }
            }
        }
    }
}
