import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기

public class BJ2206 {

    private static class Point {
        int x;
        int y;
        int cnt;
        boolean flag;

        Point(int x, int y, int cnt, boolean flag) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;         // 거리
            this.flag = flag;       // 벽을 부쉈는지 여부
        }
    }
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];
        visited = new boolean[2][n+1][m+1];     // 벽을 부순 경우 or 안 부순 경우 로 나뉘기에 3차원 배열 선언
        
        for(int i = 1; i <= n; i++) {
            String input = br.readLine();
            for(int j = 0; j < m; j++) {
                if(input.charAt(j) == '1') map[i][j+1] = 1;
            }
        }
        
        solution();
        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    private static void solution() {

        queue.add(new Point(1, 1, 1, false));
        visited[0][1][1] = true;

        while(!queue.isEmpty()) {
            Point poll = queue.poll();

            if(poll.x == n && poll.y == m) {
                answer = Math.min(answer, poll.cnt);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx <= 0 || xx > n || yy <= 0 || yy > m) continue;
                if(poll.flag == true && visited[1][xx][yy]) continue;
                if(poll.flag == false && visited[0][xx][yy]) continue;

                if(poll.flag) {     // 이미 벽을 부쉈다면
                    if(map[xx][yy] == 0) {
                        queue.add(new Point(xx, yy, poll.cnt+1, true));     // flag == true로 부순 상태 유지
                        visited[1][xx][yy] = true;
                    }
                }
                else {      // 아직 벽을 부수지 않았다면
                    if(map[xx][yy] == 1) {      // 벽을 부숴 통과
                        queue.add(new Point(xx, yy, poll.cnt+1, true));     // flag == true로 부순 상태 설정
                        visited[1][xx][yy] = true;
                    }
                    else {
                        queue.add(new Point(xx, yy, poll.cnt+1, false));    // 부수지 않고 통과하기에 flag == false로 상태 설정
                        visited[0][xx][yy] = true;
                    }
                }
            }
        }

    }
    
}
