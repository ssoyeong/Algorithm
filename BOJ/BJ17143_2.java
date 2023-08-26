import java.io.*;
import java.util.*;

// 낚시왕

public class BJ17143_2 {
    
    static class Shark {
        int id, r, c, s, d, z;

        Shark(int id, int r, int c, int s, int d, int z) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

    }
    static int n, m, num;       // 격자판의 가로, 세로, 상어의 수
    static int[][] board;
    static Shark[] sharks;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        sharks = new Shark[num + 1];

        for(int i = 1; i <= num; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            
            sharks[i] = new Shark(i, r, c, s, d, z);
            board[r][c] = i;
        }

        System.out.println(solution());
    }

    private static int solution() {

        int ans = 0;
        for(int pos = 0; pos < m; pos++) {      // pos는 낚시왕의 위치

            // 가장 가까운 상어 잡기
            for(int i = 0; i < n; i++) {
                if(board[i][pos] != 0) {
                    int id = board[i][pos];
                    ans += sharks[id].z;
                    sharks[id].id = -1;         // 잡은 상어는 id를 -1로 변경
                    board[i][pos] = 0;
                    break;
                }
            }
            // 상어 이동하기
            moveSharks();
        }

        return ans;
    }

    private static void moveSharks() {

        int[][] tempBoard = new int[n][m];

        for(int i = 1; i <= num; i++) {
            if(sharks[i].id  == -1) continue;      // 잡힌 상어는 제외

            Shark target = sharks[i];
            int xx = target.r;
            int yy = target.c;
            for(int j = 0; j < target.s; j++) {
                xx += dx[target.d];
                yy += dy[target.d];

                if(xx < 0 || xx >= n || yy < 0 || yy >= m) {
                    target.d = target.d % 2 == 0 ? target.d - 1 : target.d + 1;
                    xx += dx[target.d] * 2;
                    yy += dy[target.d] * 2;
                }
            }

            if(tempBoard[xx][yy] == 0) {                            // 다른 상어가 없는 경우
                tempBoard[xx][yy] = target.id;
                target.r = xx;
                target.c = yy;
            }
            else {
                if(sharks[tempBoard[xx][yy]].z > target.z) {        // 다른 상어의 크기가 더 큰 경우
                    sharks[target.id].id = -1;
                }
                else {                                              // 다른 상어의 크기가 더 작은 경우
                    sharks[tempBoard[xx][yy]].id = -1;
                    tempBoard[xx][yy] = target.id;
                    target.r = xx;
                    target.c = yy;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                board[i][j] = tempBoard[i][j];
            }
        }
    }
}
