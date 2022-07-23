import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 달팽이

public class BJ1913 {

    static int n, target;
    static int num;
    static int start_x, start_y;
    static int[][] table;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        target = Integer.parseInt(br.readLine());
         
        table = new int[n+1][n+1];
        start_x = (n+1)/2;
        start_y = (n+1)/2;

        table[start_x][start_y] = 1;
        for(int iter = 3; iter <= n; iter += 2) {
            start_x--;
            start_y--;
            insert(iter);
        }

        print();
    }

    private static void insert(int iter) {

        num = iter * iter;
        for(int i = 0; i < 4; i++) {
            line(i, iter);
        }
    }

    private static void line(int idx, int iter) {

        int end = (idx == 4) ? iter : iter-1;
        
        for(int i = 0; i < end; i++) {
            table[start_x][start_y] = num--;
            start_x += dx[idx];
            start_y += dy[idx];
        }
    }

    private static void print() {

        int target_x = -1, target_y = -1; 

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                sb.append(table[i][j] + " ");

                if(table[i][j] == target) {
                    target_x = i;
                    target_y = j;
                }
            }
            sb.append("\n");
        }

        String target = target_x + "" + " " + target_y + "";
        sb.append(target + "\n");
        System.out.println(sb.toString());
    }
}
