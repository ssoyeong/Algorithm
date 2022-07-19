import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 게임 개발

public class BJ1516 {

    static int n;
    static int[] cost;
    static int[] numOfParents;
    static int[] endTime;
    static ArrayList<Integer>[] pre;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n+1];
        numOfParents = new int[n+1];
        endTime = new int[n+1];
        pre = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            pre[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int x = Integer.parseInt(st.nextToken());
                if(x == -1) break;
                pre[x].add(i);
                numOfParents[i]++;
            }
        }

        solution();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(endTime[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void solution() {

        for(int i = 1; i <= n; i++) {
            if(numOfParents[i] == 0) {
                queue.add(i);
                endTime[i] = cost[i];
            }
        }

        while(!queue.isEmpty()) {
            int poll = queue.poll();
            for(int i = 0; i < pre[poll].size(); i++) {
                int target = pre[poll].get(i);
                numOfParents[target]--;
                endTime[target] = Math.max(endTime[target], endTime[poll] + cost[target]);
            
                if(numOfParents[target] == 0) queue.add(target);
            }
        }
    }
    
}
