import java.io.*;
import java.util.*;

// Puyo Puyo

public class BJ11559 {

    static final int N = 12;
    static final int M = 6;
    static char[][] board = new char[N][M];
    static boolean[][] visited = new boolean[N][M];
    static ArrayDeque<int[]> queue = new ArrayDeque<>();               // BFS 탐색을 위한 큐
    static ArrayDeque<Character> temp = new ArrayDeque<>();            // 뿌요 떨어뜨릴 때 사용하는 큐
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();
        }

        System.out.println(solution());
    }

    private static int solution() {

        int ans = 0;
        
        while(true) {

            boolean isSequence = false;
            // visited[][] 초기화
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    visited[i][j] = false;
                }
            }

            // 뿌요 터뜨리기
            for(int i = N - 1; i >= 0; i--) {
                for(int j = 0; j < M; j++) {
                    if(board[i][j] != '.' && !visited[i][j]) {
                        
                        // tempBoard에 board 복사하기
                        char[][] tempBoard = new char[N][M];
                        for(int r = 0; r < N; r++) {
                            for(int c = 0; c < M; c++) {
                                tempBoard[r][c] = board[r][c];
                            }
                        }
                        // (i, j)를 기준으로 BFS 탐색하기
                        boolean isbombed = bfs(i, j, board[i][j]);
                        // 터지지 않았다면 board 되돌리기
                        if(!isbombed) {
                            for(int r = 0; r < N; r++) {
                                for(int c = 0; c < M; c++) {
                                    board[r][c] = tempBoard[r][c];
                                }
                            }
                        }
                        else isSequence = true;
                    }
                }
            }

            if(!isSequence) return ans;     // 연쇄적으로 폭발이 일어나지 않았다면, 종료

            // 뿌요 아래로 떨어뜨리기
            for(int j = 0; j < M; j++) {
                for(int i = N - 1; i >= 0; i--) {
                    if(board[i][j] != '.') {
                        temp.add(board[i][j]);
                        board[i][j] = '.';
                    }
                }

                int idx = N - 1;
                while(!temp.isEmpty()) {
                    board[idx--][j] = temp.poll();
                }
            }

            ans++;
        }
    }
    
    private static boolean bfs(int x, int y, char ch) {      // 뿌요가 4개 이상 모여 터졌는지의 여부
        visited[x][y] = true;
        board[x][y] = '.';
        queue.add(new int[] {x, y});

        int cnt = 1;
        while(true) {
            int[] poll = queue.poll();
            for(int i = 0; i < 4; i++) {
                int xx = poll[0] + dx[i];
                int yy = poll[1] + dy[i];

                if(xx < 0 || xx >= N || yy < 0 || yy >= M) continue;
                if(board[xx][yy] != ch || visited[xx][yy]) continue;

                visited[xx][yy] = true;
                board[xx][yy] = '.';
                queue.add(new int[] {xx, yy, cnt++});
            }

            if(queue.isEmpty()) {
                if(cnt < 4) return false;
                return true;
            }
        }
    }

}
