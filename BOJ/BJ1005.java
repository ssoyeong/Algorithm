import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ACM Craft

public class BJ1005 {

    private static class Building {

        int num;
        int cost;

        Building(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static int t, n, k, w;
    static int[] numOfParents;
    static int[] time;
    static int[] dp;
    static ArrayList<Integer>[] children;
    static Queue<Building> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test = 0; test < t; test++) {

            init(br);
            int result = solution();
            sb.append(result + "\n");
        }

        System.out.println(sb.toString());
    }
    
    private static void init(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        numOfParents = new int[n+1];
        time = new int[n+1];
        dp = new int[n+1];
        children = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
                
            children[x].add(y);
            numOfParents[y]++;
        }
            
        w = Integer.parseInt(br.readLine());
    }

    private static int solution() {

        queue.clear();

        for(int i = 1; i <= n; i++) {
            if(numOfParents[i] == 0) {
                if(i == w) return time[i];
                queue.add(new Building(i, time[i]));
                
            }
        }

        while(!queue.isEmpty()) {

            Building poll = queue.poll();
            for(int i = 0; i < children[poll.num].size(); i++) {

                int target = children[poll.num].get(i);
                numOfParents[target]--;
                dp[target] = Math.max(dp[target], poll.cost);

                if(numOfParents[target] == 0) {
                    if(target == w) return time[target] + dp[target];
                    queue.add(new Building(target, time[target] + dp[target]));             
                }
            }
        }

        return -99999;
    }
}
