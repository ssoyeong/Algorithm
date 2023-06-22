import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 청소년 상어

public class BJ19236 {

    private static class Fish{
        int x;
        int y;
        int dir;        // 1(위쪽)부터 순서대로 반시계 방향

        Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static final int SIZE = 4;
    static int ans;
    static Fish[] fishes = new Fish[SIZE * SIZE + 1];           // 물고기 위치 및 방향 정보를 담는 배열
    static int[][] board = new int[SIZE][SIZE];                 // 물고기 번호를 담는 배열 (상어는 0, 빈칸은 -1)
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < SIZE; j++) {

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                fishes[a] = new Fish(i, j, b);
                board[i][j] = a;
            }
        }

        // 상어가 (0, 0)에 들어감
        int total = board[0][0];
        fishes[0] = new Fish(0, 0, fishes[board[0][0]].dir);
        fishes[board[0][0]] = new Fish(-1, -1, -1);       // 상어가 들어간 경우
        board[0][0] = 0;

        backTracking(0, total);
        System.out.println(ans);
    }

    private static void backTracking(int depth, int total) {

        // 물고기 이동
        moveFishes();

        // 배열 복사
        Fish[] tempFishes = new Fish[17];
        int[][] tempBoard = new int[SIZE][SIZE];
        for(int i = 0; i < tempFishes.length; i++) {
            tempFishes[i] = fishes[i];
        }
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                tempBoard[i][j] = board[i][j];
            }
        }

        // 상어 이동
        boolean flag = false;           // 상어가 더 이상 이동할 수 있는 칸이 존재하는지 여부
        int sharkDir = fishes[0].dir;

        for(int i = 1; i < SIZE; i++) {

            int xx = fishes[0].x + dx[sharkDir] * i;
            int yy = fishes[0].y + dy[sharkDir] * i;

            if(xx < 0 || xx >= SIZE || yy < 0 || yy >= SIZE) continue;

            if(board[xx][yy] != -1) {
                flag = true;
                
                int targetFishNum = board[xx][yy];
                Fish target = fishes[board[xx][yy]];
                
                board[fishes[0].x][fishes[0].y] = -1;
                fishes[0] = new Fish(xx, yy, target.dir);
                fishes[targetFishNum] = new Fish(-1, -1, -1);
                board[xx][yy] = 0;

                backTracking(depth + 1, total + targetFishNum);

                // 배열 복사
                for(int j = 0; j < tempFishes.length; j++) {
                    fishes[j] = tempFishes[j];
                }
                for(int j = 0; j < SIZE; j++) {
                    for(int k = 0; k < SIZE; k++) {
                        board[j][k] = tempBoard[j][k];
                    }
                }                
            }
        }

        // 종료 조건
        if(!flag) {
            ans = Integer.max(ans, total);
            return;
        }
    }

    private static void moveFishes() {

        for(int i = 1; i < fishes.length; i++) {

            Fish fish = fishes[i];
            if(fish.x == -1) continue;                  // 상어인 경우

            for(int j = 0; j <= 7; j++) {

                int direction = (fish.dir + j) % 8;     // 방향 설정
                if(direction == 0) direction = 8;

                int xx = fish.x + dx[direction];
                int yy = fish.y + dy[direction];

                // 이동할 수 없는 경우
                if(xx < 0 || xx >= SIZE || yy < 0 || yy >= SIZE) continue;
                if(board[xx][yy] == 0) continue;

                // 이동할 수 있는 경우
                if(board[xx][yy] == -1) {               // 빈 칸인 경우
                    board[xx][yy] = i;
                    board[fish.x][fish.y] = -1;
                    fishes[i] = new Fish(xx, yy, direction);
                }
                else {                                  // 다른 물고기가 있는 경우
                    int targetNum = board[xx][yy];
                    board[xx][yy] = i;
                    board[fish.x][fish.y] = targetNum;

                    fishes[i] = new Fish(xx, yy, direction);
                    fishes[targetNum] = new Fish(fish.x, fish.y, fishes[targetNum].dir);
                }

                break;
            }
        }
    }
    
}