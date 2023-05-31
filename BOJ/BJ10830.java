import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

// 행렬 제곱

public class BJ10830 {

    static int n;
    static long b;
    static int[][] matrix;

    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());
        matrix = new int[n+1][n+1];
        
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] ans = solution(b);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int[][] solution(long time) {

        int[][] temp = new int[n+1][n+1];

        if(time == 1) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    temp[i][j] = matrix[i][j] % 1000;
                }
            }
            return temp;
        }

        temp = solution(time / 2);

        int[][] result = calculateMatrix(temp, temp);
        if(time % 2 == 1) {
            result = calculateMatrix(result, matrix);
        }

        return result;
    }

    private static int[][] calculateMatrix(int[][] first, int[][] second) {

        int[][] result = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                result[i][j] = multiple(first, second, i, j);
            }
        }

        return result;
    }

    private static int multiple(int[][] first, int[][] second, int r, int c) {

        int total = 0;
        for(int i = 1; i <= n; i++) {
            total += (first[r][i] * second[i][c]) % 1000;
        }

        return total % 1000;
    }
    
}
