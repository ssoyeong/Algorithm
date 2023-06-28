import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 성곽

public class BJ2234 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static int numOfRooms;
    static int maxRoomSize, maxRoomSizeDeleted;
    static int[][] board;
    static int[][] roomNum;                                     // 0이라면 아직 방문하지 않은 칸
    static Queue<Point> queue = new LinkedList<>();
    static ArrayList<Integer> roomSize = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1};                            // 서, 북, 동, 남 방향
    static int[] dy = {-1, 0, 1, 0};
    

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[m][n];
        roomNum = new int[m][n];
        
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        
        StringBuilder sb = new StringBuilder();
        sb.append(numOfRooms + "\n");
        sb.append(maxRoomSize + "\n");
        sb.append(maxRoomSizeDeleted + "\n");
        System.out.println(sb.toString());
        
    }

    private static void solution() {

        // 방을 구분해서 방 개수 찾기
        searchRooms();

        // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기
        searchMaxRoomSizeDeleted();
        
    }

    private static void searchRooms() {

        roomSize.add(0);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                
                if(roomNum[i][j] != 0) continue;        // 이미 방문한 방은 방 번호가 매겨져 있음
                
                queue.clear();
                numOfRooms++;
                int area = 0;                           // 하나의 방의 크기

                // numOfRooms에 해당하는 방 탐색
                roomNum[i][j] = numOfRooms;
                area++;
                queue.add(new Point(i, j));

                while(true) {
                    Point poll = queue.poll();
                    int target = board[poll.x][poll.y];

                    for(int d = 0; d < 4; d++) {
                        if((target & (1 << d)) == 0) {
                            int xx = poll.x + dx[d];
                            int yy = poll.y + dy[d];

                            if(xx < 0 || xx >= m || yy < 0 || yy >= n) continue;
                            if(roomNum[xx][yy] != 0) continue;

                            roomNum[xx][yy] = numOfRooms;
                            area++;
                            queue.add(new Point(xx, yy));
                        }
                    }

                    if(queue.isEmpty()) {
                        roomSize.add(area);
                        maxRoomSize = Integer.max(maxRoomSize, area);
                        break;
                    }
                }
            }
        }
    }

    private static void searchMaxRoomSizeDeleted() {

        boolean[][] visited = new boolean[m][n];
        queue.clear();
        queue.add(new Point(0, 0));

        while(!queue.isEmpty()) {

            Point poll = queue.poll();
            int curRoomNum = roomNum[poll.x][poll.y];

            for(int d = 0; d < 4; d++) {

                int xx = poll.x + dx[d];
                int yy = poll.y + dy[d];

                if(xx < 0 || xx >= m || yy < 0 || yy >= n) continue;
                if(visited[xx][yy]) continue;

                int targetRoomNum = roomNum[xx][yy];
                if(curRoomNum != targetRoomNum) {       // 다른 방과 인접하다면, 방 크기를 합쳐봄
                    
                    int sum = roomSize.get(curRoomNum) + roomSize.get(targetRoomNum);
                    maxRoomSizeDeleted = Integer.max(maxRoomSizeDeleted, sum);
                }

                visited[xx][yy] = true;
                queue.add(new Point(xx, yy));
            }
        }
    }

}
