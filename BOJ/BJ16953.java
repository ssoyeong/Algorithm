import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// A → B

public class BJ16953 {

    private static class Node {
        long num;
        long cnt;

        Node(long num, long cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static long a, b;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        
        long ans = solution();
        System.out.println(ans);
    }

    private static long solution() {

        queue.add(new Node(a, 0));

        while(!queue.isEmpty()) {

            Node poll = queue.poll();

            if(poll.num == b) {
                return poll.cnt + 1;
            }

            if(poll.num * 2 <= b) {
                queue.add(new Node(poll.num * 2, poll.cnt + 1));
            }

            if(poll.num * 10 +1 <= b) {
                queue.add(new Node(poll.num * 10 + 1, poll.cnt + 1));
            }
        }

        return -1;
    }
    
}
