import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA1961 {

    static int t;
    static int n;
    static String[][] board;
    static String[][] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= t; tc++) {

            n = Integer.parseInt(br.readLine());
            board = new String[n][n];
            answer = new String[n][3];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    board[i][j] = st.nextToken();
                }
            }
            
            solution();

            sb.append("#" + tc + "\n");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < 3; j++) {
                    sb.append(answer[i][j] + " ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
    
    private static void solution() {    

        // 90도 회전
        for(int i = 0; i < n; i++) {
            String line = "";
            for(int j = 0; j < n; j++) {
                line = line.concat(board[n - j - 1][i]);
            }
            answer[i][0] = line;
        }

        // 180도 회전
        for(int i = n - 1; i >= 0; i--) {
            String line = "";
            for(int j = n - 1; j >= 0; j--) {
                line = line.concat(board[i][j]);
            }
            answer[n - i - 1][1] = line;
        }
        
        // 270도 회전
        for(int i = 0; i < n; i++) {
            String line = "";
            for(int j = 0; j < n; j++) {
                line = line.concat(board[j][i]);
            }
            answer[n - i - 1][2] = line;
        }
    }

}
