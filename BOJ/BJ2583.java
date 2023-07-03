import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 영역 구하기

public class BJ2583 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y =y;
        }
    }
    static int m, n, k;
    static boolean[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> queue = new LinkedList<>();
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            drawRectangle(x1, y1, x2, y2);
        }

        // 직사각형을 그린 후, 분리된 각 영역의 넓이를 계산
        solution();

        StringBuilder sb = new StringBuilder();
        Collections.sort(answer);
        sb.append(answer.size() + "\n");
        for(int area : answer) {
            sb.append(area + " ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void drawRectangle(int x1, int y1, int x2, int y2) {

        for(int i = x1; i < x2; i++) {
            for(int j = y1; j < y2; j++) {
                board[i][j] = true;
            }
        }
    }

    private static void solution() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] || visited[i][j]) continue;

                int area = 1;
                visited[i][j] = true;
                queue.add(new Point(i, j));

                while(!queue.isEmpty()) {
                    Point poll = queue.poll();
                    
                    for(int d = 0; d < 4; d++) {
                        int xx = poll.x + dx[d];
                        int yy = poll.y + dy[d];

                        if(xx < 0 || xx >= n || yy < 0 || yy >= m) continue;
                        if(board[xx][yy] || visited[xx][yy]) continue;

                        area++;
                        visited[xx][yy] = true;
                        queue.add(new Point(xx, yy));
                    }
                }

                answer.add(area);
            }
        }
    }
    
}
