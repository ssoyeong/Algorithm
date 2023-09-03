import java.io.*;
import java.util.*;

// 스타트 택시

public class BJ19238 {
    
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int dist;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if(this.dist == o.dist) {
                if(this.x == o.x) return this.y - o.y;
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }
    static int n, m;
    static int fuel;
    static int numOfRemainGuests;
    static boolean isValid = true;
    static Point taxi;
    static int[][] board;
    static Point[][] guests;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static ArrayDeque<Point> queue = new ArrayDeque<>();
    static PriorityQueue<Point> candidates = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        numOfRemainGuests = m;
        board = new int[n][n];
        guests = new Point[m + 1][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                if(st.nextToken().equals("1")) board[i][j] = -1;            // 벽인 경우 -1 저장
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        for(int i = 1; i<= m; i++) {
            st = new StringTokenizer(br.readLine());
            guests[i][0] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            guests[i][1] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            board[guests[i][0].x][guests[i][0].y] = i;
        }
        
        solution();
        System.out.println(isValid ? fuel : -1);
    }

    private static void solution() {

        while(true) {

            // 1. 택시 위치에서 BFS 탐색을 통해 가장 가까운 승객 찾기
            if(!pickUpGuest()) {
                isValid = false;
                return;
            }

            // 2. 찾은 승객의 ID 값 담기
            int targetGuestId = board[taxi.x][taxi.y];
            board[taxi.x][taxi.y] = 0;

            // 3. 승객의 도착지까지 이동하기
            if(!moveDestination(guests[targetGuestId][1])) {
                isValid = false;
                return;
            }

            // 4. 모든 승객을 데려다줬는지 확인
            numOfRemainGuests--;
            if(numOfRemainGuests == 0) break;
        }
    }

    private static boolean pickUpGuest() {

        // 택시가 출발한 자리에 바로 승객이 있는 경우
        if(board[taxi.x][taxi.y] > 0) return true;
        // 이동할 연료가 더 이상 없는 경우
        if(fuel == 0) return false;

        boolean[][] visited = new boolean[n][n];
        queue.clear();
        candidates.clear();

        visited[taxi.x][taxi.y] = true;
        queue.add(new Point(taxi.x, taxi.y, 0));

        while(!queue.isEmpty()) {

            Point poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n || visited[xx][yy] || board[xx][yy] == -1) continue;
                if(poll.dist + 1 < fuel) {
                    visited[xx][yy] = true;
                    if(board[xx][yy] > 0) candidates.add(new Point(xx, yy, poll.dist + 1));
                    else queue.add(new Point(xx, yy, poll.dist + 1));
                }
            }
        }

        if(!candidates.isEmpty()) {
            Point target = candidates.poll();
            taxi = new Point(target.x, target.y);
            fuel -= target.dist;
            return true;
        }

        return false;
    }

    private static boolean moveDestination(Point destination) {

        boolean[][] visited = new boolean[n][n];
        queue.clear();

        visited[taxi.x][taxi.y] = true;
        queue.add(new Point(taxi.x, taxi.y, 0));

        while(!queue.isEmpty()) {

            Point poll = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int xx = poll.x + dx[i];
                int yy = poll.y + dy[i];

                if(xx < 0 || xx >= n || yy < 0 || yy >= n || visited[xx][yy] || board[xx][yy] == -1) continue;

                if(poll.dist + 1 <= fuel) {
                    if(xx == destination.x && yy == destination.y) {
                        taxi = new Point(xx, yy);
                        fuel += (poll.dist + 1);
                        return true;
                    }

                    visited[xx][yy] = true;
                    queue.add(new Point(xx, yy, poll.dist + 1));
                }
            }
        }

        return false;
    }

}
