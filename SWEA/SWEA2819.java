import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

// 격자판의 숫자 이어 붙이기

public class SWEA2819 {

    static int t;
    static int ans;
    static int[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static HashSet<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++) {
            board = new int[4][4];
            set.clear();

            for(int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 4; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    dfs(i, j, String.valueOf(board[i][j]));
                }
            }

            ans = set.size();
            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int x, int y, String num) {

        if(num.length() == 7) {
            if(!set.contains(num)) {
                set.add(num);
            }
            return;
        }

        for(int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if(xx < 0 || xx >= 4 || yy < 0 || yy >= 4) continue;
            num = num.concat(String.valueOf(board[xx][yy]));
            dfs(xx, yy, num);
            num = num.substring(0, num.length() - 1);
        }
    }
    
}
