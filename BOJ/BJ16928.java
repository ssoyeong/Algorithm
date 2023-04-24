import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 뱀과 사다리 게임

public class BJ16928 {

    private static class Node {

        int pos;
        int cnt;

        Node(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }
    
    static int n, m;
    static int ans = 100;
    static int[] visited = new int[101];
    static HashMap<Integer, Integer> map = new HashMap<>();
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        Arrays.fill(visited,100);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.put(a, b);
        }

        queue.add(new Node(1, 0));
        visited[1] = 0;
        bfs();

        System.out.println(ans);
    }

    private static void bfs() {

        while(!queue.isEmpty()) {

            Node poll = queue.poll();

            if(poll.pos == 100) {
                ans = Math.min(ans, poll.cnt);
            }
            else {
                if(map.containsKey(poll.pos)) {
                    int newPos = map.get(poll.pos);
                    if(visited[newPos] > poll.cnt) {
                        queue.add(new Node(newPos, poll.cnt));
                        visited[newPos] = poll.cnt;
                    }
                }
                else {
                    for(int i = 1; i <= 6; i++) {
                        int newPos = poll.pos + i;
                        if(newPos <= 100 && visited[newPos] > poll.cnt) {
                            queue.add(new Node(newPos, poll.cnt + 1));
                            visited[newPos] = poll.cnt + 1;
                        }
                    }
                }
            }
        }
    }
}

