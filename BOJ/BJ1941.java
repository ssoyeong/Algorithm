import java.io.*;
import java.util.*;

// 소문난 칠공주

public class BJ1941 {

    static int ans;
    static char[][] board = new char[5][5];
    static ArrayDeque<Integer> queue = new ArrayDeque<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }

        comb(0, 0, new int[7]);
        System.out.println(ans);
    }

    private static void comb(int depth, int idx, int[] selected) {

        if(idx == 7) {
            if(bfs(selected)) ans++;
            return;
        }
        if(depth == 25) return;

        selected[idx] = depth;
        comb(depth + 1, idx + 1, selected);
        comb(depth + 1, idx, selected);
    }

    private static boolean bfs(int[] selected) {

        boolean[] visited = new boolean[7];
        int cntAll = 0, cntSom = 0;

        visited[0] = true;
        queue.add(0);
        cntAll++;
        if(board[selected[0] / 5][selected[0] % 5] == 'S') cntSom++;

        while(!queue.isEmpty()) {
            int poll = queue.poll();

            for(int d = 0; d < 4; d++) {
                int xx = (selected[poll] / 5) + dx[d];
                int yy = (selected[poll] % 5) + dy[d];

                for(int i = 1; i < 7; i++) {
                    if(!visited[i] && xx == selected[i] / 5 && yy == selected[i] % 5) {
                        visited[i] = true;
                        queue.add(i);
                        cntAll++;
                        if(board[xx][yy] == 'S') cntSom++;
                        break;
                    }
                }
            }
        }

        if(cntAll == 7 && cntSom >= 4) return true;
        return false;
    }
}