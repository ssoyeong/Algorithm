import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 상어 초등학교

public class BJ21608 {

    private static class Student {

        Integer[] love = new Integer[4];
        int r;
        int c;

        Student(int x1, int x2, int x3, int x4) {
            this.love[0] = x1;
            this.love[1] = x2;
            this.love[2] = x3;
            this.love[3] = x4;
        }
    }
    private static class Node implements Comparable<Node> {

        int r;
        int c;
        int nearEmpty;
        int cnt;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Node(int r, int c, int nearEmpty, int cnt) {
            this.r = r;
            this.c = c;
            this.nearEmpty = nearEmpty;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cnt == o.cnt) {
                if(this.nearEmpty == o.nearEmpty) {
                    if(this.r == o.r) return this.c - o.c;
                    return this.r - o.r;
                }
                return o.nearEmpty - this.nearEmpty;
            }
            return o.cnt - this.cnt;
        }
    }
    static int n;
    static int[][] board;
    static int[][] adj;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Student[] arr;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];
        adj = new int[n+1][n+1];
        arr = new Student[n*n+1];

        Queue<Integer> order = new LinkedList<>();
        StringTokenizer st;
        for(int i = 0; i < n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            
            order.add(x);
            arr[x] = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        while(!order.isEmpty()) {

            int target = order.poll();
            setSeat(target);
        }

        System.out.println(calculateAnswer());
    }

    private static void setSeat(int target) {

        // 초기화
        for(int i = 1; i <= n; i++) {
            Arrays.fill(adj[i], 0);
        }
        queue.clear();

        // 비어있는 칸 중에서 love가 인접한 칸 찾기
        for(int i = 0; i < 4; i++) {
            int loveNum = arr[target].love[i];
            int love_r = arr[loveNum].r;
            int love_c = arr[loveNum].c;

            if(love_r == 0 || love_c == 0) continue;    

            // 특정 love의 인접한 칸 탐색
            for(int j = 0; j < 4; j++) {
                int rr = love_r + dx[j];
                int cc = love_c + dy[j];
                if(rr < 1 || rr > n || cc < 1 || cc > n) continue;
                if(board[rr][cc] != 0) continue;
                
                int numOfNearEmpty = searchNumOfNearEmpty(rr, cc);
                if(adj[rr][cc] != 0) {
                    queue.remove(new Node(rr, cc));
                    adj[rr][cc]++;
                    queue.add(new Node(rr, cc, numOfNearEmpty, adj[rr][cc]));
                }
                else {
                    adj[rr][cc]++;
                    queue.add(new Node(rr, cc, numOfNearEmpty, 1));
                }
            }
        }

        // 최종 자리 선정
        Node poll = queue.poll();
        if(poll == null) {
            // 좋아하는 학생이 아무도 앉아있지 않은 경우
            poll = setSeatForNone();
        }
        board[poll.r][poll.c] = target;
        arr[target].r = poll.r;
        arr[target].c = poll.c;
    }

    private static int searchNumOfNearEmpty(int r, int c) {

        int result = 0;

        for(int i = 0; i < 4; i++) {
            int rr = r + dx[i];
            int cc = c + dy[i];
            if(rr < 1 || rr > n || cc < 1 || cc > n) continue;
            if(board[rr][cc] == 0) result++;
        }

        return result;
    }

    private static Node setSeatForNone() {

        queue.clear();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(board[i][j] != 0) continue;
                int numOfNearEmpty = searchNumOfNearEmpty(i, j);
                queue.add(new Node(i, j, numOfNearEmpty, 1));
            }
        }

        return queue.poll();
    }
    
    private static int calculateAnswer() {

        int answer = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {

                int target = board[i][j];
                int cnt = 0;
                for(int k = 0; k < 4; k++) {
                    int rr = i + dx[k];
                    int cc = j + dy[k];
                    if(rr < 1 || rr > n || cc < 1 || cc > n) continue;

                    int friend = board[rr][cc];
                    if(Arrays.asList(arr[target].love).contains(friend)) cnt++;
                }
                if(cnt != 0) {
                    answer += Math.pow(10, cnt-1);
                }
            }
        }
        return answer;
    }
    
}
