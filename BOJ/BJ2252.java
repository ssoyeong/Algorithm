import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 줄 세우기

public class BJ2252 {

    static int n, m;
    static int[] numOfParents;
    static ArrayList<Integer>[] pre;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        numOfParents = new int[n+1];
        pre = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            pre[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            numOfParents[b]++;
            pre[a].add(b);
        }

        StringBuilder sb = new StringBuilder();
        solution(sb);
        System.out.println(sb.toString());
    }

    private static void solution(StringBuilder sb) {

        for(int i = 1; i <= n; i++) {
            if(numOfParents[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int poll = queue.poll();
            sb.append(poll + " ");

            for(int i = 0; i < pre[poll].size(); i++) {
                int x = pre[poll].get(i);
                numOfParents[x]--;
                if(numOfParents[x] == 0) queue.add(x);
            }
        }
    }
    
}
