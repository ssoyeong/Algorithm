import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 작업

public class BJ2056 {

    static int n;
    static int[] cost;
    static int[] numOfParents;
    static int[] endTime;
    static ArrayList<Integer>[] children;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n+1];
        numOfParents = new int[n+1];
        endTime = new int[n+1];
        children = new ArrayList[n+1];

        for(int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            numOfParents[i] = n;
            for(int j = 0; j < n; j++) {
                children[Integer.parseInt(st.nextToken())].add(i);
            }
        }
   
        solution();

        Arrays.sort(endTime);
        System.out.println(endTime[n]);

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

            for(int j = 0; j < children[poll].size(); j++) {
                int x = children[poll].get(j);
                numOfParents[x]--;
                endTime[x] = Math.max(endTime[x], endTime[poll] + cost[x]);
                if(numOfParents[x] == 0) {
                    queue.add(x);
                }
            }
        }
    }
    
}
