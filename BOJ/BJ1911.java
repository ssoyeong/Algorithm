import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 흙길 보수하기

public class BJ1911 {
    
    private static class Node implements Comparable<Node> {

        int start;
        int end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return this.start - o.start;
        }
    }
    static int n, l;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            queue.add(new Node(start, end));
        }

        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {

        int cnt = 0;
        int idx = 0;

        while(!queue.isEmpty()) {

            Node poll = queue.poll();
            if(idx < poll.start) idx = poll.start;

            while(idx < poll.end) {
                cnt++;
                idx += l;
            }
        } 

        return cnt;
    }
}
