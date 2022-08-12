import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 음악프로그램

public class BJ2623 {

    static int n, m;
    static int cnt;
    static int[] numOfParents;
    static ArrayList<Integer>[] children;
    static ArrayList<Integer>[] parents;
    static Queue<Integer> queue = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numOfParents = new int[n+1];
        children = new ArrayList[n+1];
        parents = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
            parents[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            int pre = -1;
            for(int j = 0; j < num; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(pre != -1) {
                    if(!children[pre].contains(x)) {
                        children[pre].add(x);
                    }
                    if(!parents[x].contains(pre)) {
                        parents[x].add(pre);
                    }
                }
                pre = x;
            }
        }

        for(int i = 1; i <= n; i++) {
            numOfParents[i] = parents[i].size();
            if(numOfParents[i] == 0) {
                queue.add(i);
            }
        }

        solution();
        System.out.println(sb.toString());
    }

    private static void solution() {

        while(!queue.isEmpty()) {
            int poll = queue.poll();
            sb.append(poll + "\n");
            cnt++;

            for(int i = 0; i < children[poll].size(); i++) {
                numOfParents[children[poll].get(i)]--;
                if(numOfParents[children[poll].get(i)] == 0) {
                    queue.add(children[poll].get(i));
                }
            }
        }

        if(cnt < n) {
            System.out.println(0);
            System.exit(0);
        }
    }
    
}
