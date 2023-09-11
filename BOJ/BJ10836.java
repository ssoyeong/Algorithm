import java.io.*;
import java.util.*;

// 여왕벌

public class BJ10836 {
    
    static int m, n;
    static int[] board;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[m * 2 - 1];

        for(int day = 0; day < n; day++) {

            st = new StringTokenizer(br.readLine());
            int idx = 0;
            for(int i = 0; i < 3; i++) {
                int x = Integer.parseInt(st.nextToken());
                for(int j = 0; j < x; j++) {
                    board[idx++] += i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            sb.append((board[m - 1 - i] + 1) + " ");
            for(int j = m; j < 2 * m - 1; j++) {
                sb.append((board[j] + 1) + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
