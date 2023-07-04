import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1974 {

    static int t;
    static int[][] board = new int[9][9];
    static boolean[] visited = new boolean[10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= t; tc++) {

            for(int i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 9; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if(solution()) {
                sb.append("#" + tc + " 1\n");
            }
            else {
                sb.append("#" + tc + " 0\n");
            }
        }

        System.out.println(sb.toString());
    }
    
    private static boolean solution() {

        // 행 탐색
        for(int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for(int j = 0; j < 9; j++) {
                int target = board[i][j];

                if(visited[target]) return false;
                visited[target] = true;
            }
        }

        // 열 탐색
        for(int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for(int j = 0; j < 9; j++) {
                int target = board[j][i];

                if(visited[target]) return false;
                visited[target] = true;
            }
        }

        // 3x3 영역 탐색
        for(int i = 0; i < 9; i += 3) {
            for(int j = 0; j < 9; j += 3) {
                boolean flag = search(i, j);

                if(!flag) return false;
            }
        }

        return true;
    }

    private static boolean search(int startX, int startY) {

        Arrays.fill(visited, false);

        for(int i = startX; i < startX + 3; i++) {
            for(int j = startY; j < startY + 3; j++) {

                int target = board[i][j];
                
                if(visited[target]) return false;
                visited[target] = true;
            }
        }
        
        return true;
    }
}
