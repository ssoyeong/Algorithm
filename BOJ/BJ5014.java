import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 스타트링크

public class BJ5014 {

    static class Node {
        int floor;
        int cnt;

        Node(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
    }

    static int f, s, g, u, d;
    static boolean[] visited;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visited = new boolean[f+1];

        if((s < g && u == 0) || (g < s && d == 0)) {
            System.out.println("use the stairs");
            return;
        }

        queue.add(new Node(s, 0));
        visited[s] = true;

        int ans = solution();
        if(ans > -1) System.out.println(ans);
        else System.out.println("use the stairs");
    }
    
    private static int solution(){

        while(!queue.isEmpty()) {

            Node poll = queue.poll();
            if(poll.floor == g) return poll.cnt;

            Node up = new Node(poll.floor + u, poll.cnt + 1);
            Node down = new Node(poll.floor - d, poll.cnt + 1);

            if(up.floor <= f && !visited[up.floor]) {
                queue.add(up);
                visited[up.floor] = true;
            }
            if(down.floor >= 1 && !visited[down.floor]) {
                queue.add(down);
                visited[down.floor] = true;
            }
        }

        return -1;
    }
    
}
