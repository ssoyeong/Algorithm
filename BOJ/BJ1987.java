import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 알파벳

public class BJ1987 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int r, c;
    static int answer;
    static Character[][] board;
    static boolean[][] visited;
    static boolean[] alphabet = new boolean[26];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new Character[r+1][c+1];
        visited = new boolean[r+1][c+1];

        for(int i = 1; i <= r; i++) {
            String line = br.readLine();
            for(int j = 0; j < c; j++) {
                board[i][j+1] = line.charAt(j);
            }
        }

        visited[1][1] = true;
        alphabet[(int)board[1][1] - 65]= true;
        
        backTracking(1, new Point(1, 1));

        System.out.println(answer);
    }

    private static void backTracking(int level, Point point) {

        boolean flag = false;
        for(int i = 0; i < 4; i++) {
            int xx = point.x + dx[i];
            int yy = point.y + dy[i];

            if(xx < 1 || xx > r || yy < 1 || yy > c) continue;
            if(visited[xx][yy] || alphabet[(int)board[xx][yy] - 65]) continue;
            
            flag = true;
            visited[xx][yy] = true;
            alphabet[(int)board[xx][yy] - 65] = true;
            backTracking(level +1, new Point(xx, yy));
            
            visited[xx][yy] = false;
            alphabet[(int)board[xx][yy] - 65] = false;
        }

        if(!flag) {
            answer = Math.max(answer, level);
            if(answer == 26) return;
        }
    }

    
}
