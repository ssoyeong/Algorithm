import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

// 치즈 도둑

public class SWEA7733 {
	 
    private static class Point {
        int x;
        int y;
 
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int tc;
    static int n;
    static int cntOfMaxChunks;
    static int maxNum;                  // 가장 큰 맛있는 정도
    static int[][] board;
    static boolean[][] visited;
    static Queue<Point> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        for(int t = 1; t <= tc; t++) {
 
            cntOfMaxChunks = 1;
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            visited = new boolean[n][n];
 
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    maxNum = Integer.max(maxNum, board[i][j]);
                }
            }
 
            solution();
            sb.append("#" + t + " " + cntOfMaxChunks + "\n");
        }
 
        System.out.println(sb.toString());
    }
 
    private static void solution() {
 
        for(int day = 1; day <= 100; day++) {
 
            if(day > maxNum) break;
 
            // visited 초기화
            for(int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }
            // queue 초기화
            queue.clear();
 
            // 치즈 덩어리의 개수
            int cntOfChunks = 0;
            // bfs 탐색할 시작점 선정
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(visited[i][j] || board[i][j] <= day) continue;
 
                    cntOfChunks++;
                    visited[i][j] = true;
                    queue.add(new Point(i, j));
 
                    while(!queue.isEmpty()) {
 
                        Point poll = queue.poll();
 
                        for(int d = 0; d < 4; d++) {
                            int xx = poll.x + dx[d];
                            int yy = poll.y + dy[d];
 
                            if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                            if(visited[xx][yy] || board[xx][yy] <= day) continue;
 
                            visited[xx][yy] = true;
                            queue.add(new Point(xx, yy));
                        }
                    }
                }
            }
 
            cntOfMaxChunks = Integer.max(cntOfMaxChunks, cntOfChunks);
        }
    }
}
