import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 새로운 게임 2

public class BJ17837 {

    private static class Piece {
        int x;
        int y;
        int dir;

        Piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int n, k;
    static int ans;
    static Piece[] pieces;
    static int[][] color;
    static ArrayList<Integer>[][] board;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        color = new int[n + 1][n + 1];
        board = new ArrayList[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                board[i][j] = new ArrayList<>();
            }
        }
        pieces = new Piece[k];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            
            board[x][y].add(i);
            pieces[i] = new Piece(x, y, dir);
        }

        solution();
        System.out.println(ans);
    }

    private static void solution() {

        while(true) {
            ans++;
            if(ans > 1000) {
                ans = -1;
                break;
            }

            // 1번부터 k번 말까지 이동시킴
            for(int i = 0; i < k; i++) {
                Piece target = pieces[i];
                int xx = target.x + dx[target.dir];
                int yy = target.y + dy[target.dir];
                
                if(xx < 1 || xx > n || yy < 1 || yy > n || color[xx][yy] == 2) {      // 파란색으로 이동하는 경우
                    moveToBlue(i);
                }
                else if(color[xx][yy] == 0) {                                         // 흰색으로 이동하는 경우
                    moveToWhite(i);
                }
                else {                                                                // 빨간색으로 이동하는 경우
                    moveToRed(i);
                }
            }
        }
    }

    private static void moveToBlue(int idx) {

        // 이동 방향을 반대로 바꾸기
        int currentDir = pieces[idx].dir;
        pieces[idx].dir = currentDir % 2 == 0 ? currentDir - 1 : currentDir + 1;

        Piece bottom = pieces[idx];
        // 방향을 반대로 바꾼 후 이동하려는 칸
        int xx = bottom.x + dx[bottom.dir];
        int yy = bottom.y + dy[bottom.dir];
        
        if(xx < 1 || xx > n || yy < 1 || yy > n || color[xx][yy] == 2) return;      // 해당 칸이 체스판을 벗어나거나 파란색인 경우, 가만히 있음
        else if(color[xx][yy] == 0) moveToWhite(idx);                               // 해당 칸이 흰색인 경우
        else if(color[xx][yy] == 1) moveToRed(idx);                                 // 해당 칸이 빨간색인 경우
    }

    private static void moveToWhite(int idx) {

        Piece bottom = pieces[idx];
        int xx = bottom.x + dx[bottom.dir];
        int yy = bottom.y + dy[bottom.dir];

        boolean flag = false;
        ArrayList<Integer> tempPieces = new ArrayList<>();
        for(int i = 0; i < board[bottom.x][bottom.y].size(); i++) {
            tempPieces.add(board[bottom.x][bottom.y].get(i));
        }
        
        // bottom과 bottom 위에 있는 모든 말들에 대하여
        for(int i = 0; i < tempPieces.size(); i++) {

            int targetIdx = tempPieces.get(i);

            if(targetIdx == idx) {
                flag = true;
                for(int j = tempPieces.size() - 1; j >= i; j--) {
                    board[bottom.x][bottom.y].remove(j);
                }
            }
            if(!flag) continue;
            
            // board[][]에 반영
            board[xx][yy].add(targetIdx);

            // 게임 종료 조건
            if(board[xx][yy].size() >= 4) {
                System.out.println(ans);
                System.exit(0);
            }

            // pieces[]에 반영
            pieces[targetIdx] = new Piece(xx, yy, pieces[targetIdx].dir);
        }
    }

    private static void moveToRed(int idx) {

        Piece bottom = pieces[idx];
        
        int xx = bottom.x + dx[bottom.dir];
        int yy = bottom.y + dy[bottom.dir];

        ArrayList<Integer> tempPieces = new ArrayList<>();
        for(int i = 0; i < board[bottom.x][bottom.y].size(); i++) {
            tempPieces.add(board[bottom.x][bottom.y].get(i));
        }

        // bottom과 bottom 위에 있는 모든 말들에 대하여
        for(int i = tempPieces.size() - 1; i >= 0; i--) {

            int targetIdx = tempPieces.get(i);

            // board[][]에 반영
            board[xx][yy].add(targetIdx);
            board[bottom.x][bottom.y].remove(i);
            // 게임 종료 조건
            if(board[xx][yy].size() >= 4) {
                System.out.println(ans);
                System.exit(0);
            }

            // pieces[]에 반영
            pieces[targetIdx] = new Piece(xx, yy, pieces[targetIdx].dir);
            
            if(targetIdx == idx) break;
        }
    }
}
