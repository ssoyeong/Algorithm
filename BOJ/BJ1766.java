import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 문제집

public class BJ1766 {

    static int n, m;
    static int[] numOfParents;
    static ArrayList<Integer>[] children;
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        numOfParents = new int[n+1];
        children = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            children[a].add(b);
            numOfParents[b]++;
        }

        for(int i = 1; i <= n; i++) {
            if(numOfParents[i] == 0) {
                queue.add(i);
            }
        }

        solution();
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void solution() {

        while(!queue.isEmpty()) {

            int poll = queue.poll();
            sb.append(poll + " ");
            deleteFromParents(poll);
        }
    }

    private static void deleteFromParents(int num) {

        for(int i = 0; i < children[num].size(); i++) {
            int x = children[num].get(i);
            numOfParents[x]--;
            if(numOfParents[x] == 0) queue.add(x);
        }
    }
    
}
