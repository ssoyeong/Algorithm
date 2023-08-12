import java.io.*;
import java.util.*;

// 드래곤 커브

public class BJ15685 {
    
    static final int SIZE = 100;
    static int n;
    static boolean[][] board = new boolean[SIZE + 1][SIZE + 1];
    static ArrayList<Integer> directions = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0}; 

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            
            makeDragonCurve(x, y, d, g);
        }

        int ans = calculateSquare();
        System.out.println(ans);
    }

    private static void makeDragonCurve(int x, int y, int d, int g) {

        // directions에 방향 담기
        directions.clear();
        directions.add(d);
        for(int i = 0; i < g; i++) {
            for(int idx = directions.size() - 1; idx >= 0; idx--) {
                directions.add((directions.get(idx) + 1) % 4);
            }
        }
        
        // 드래곤 커브 만들기
        board[x][y] = true;
        for(int i = 0; i < directions.size(); i++) {
            x += dx[directions.get(i)];
            y += dy[directions.get(i)];
            board[x][y] = true;
        }
    }

    private static int calculateSquare() {

        int cnt = 0;
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(board[i][j] && board[i][j + 1] && board[i + 1][j] && board[i + 1][j + 1]) cnt++;
            }
        }

        return cnt;
    }
}
