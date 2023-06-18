import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 게리맨더링 2

public class BJ17779 {

    static int n;
    static int d1, d2;
    static int totalPopulation;
    static int ans = Integer.MAX_VALUE;
    static int[][] board;
    static int[][] point = new int[4][2];       // 기준점 4개
    static boolean[][] isBorder;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];

        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                totalPopulation += board[i][j];
            }
        }

        solution();
        System.out.println(ans);
    }

    private static void solution() {

        // x, y, d1, d2 값에 따라 선거구 나누기
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                point[0][0] = i;
                point[0][1] = j;

                d1 = 1;
                while(true) {
                    if(point[0][0] + d1 > n || point[0][1] - d1 <= 0) break;

                    point[1][0] = point[0][0] + d1;
                    point[1][1] = point[0][1] - d1;

                    d2 = 1;
                    while(true) {
                        if(point[0][0] + d2 > n || point[0][1] + d2 > n) break;

                        point[2][0] = point[0][0] + d2;
                        point[2][1] = point[0][1] + d2;
                        
                        if(point[1][0] + d2 > n || point[1][1] + d2 > n) break;

                        point[3][0] = point[1][0] + d2;
                        point[3][1] = point[1][1] + d2;

                        // isBorder[][]에 경계선 체크하기
                        checkBorder(d1, d2);

                        // 나누어진 선거구에 따라 각 선거구의 인구수 구하기
                        calculatePopulation();
                
                        d2++;
                    }

                    d1++;
                }
            }
        }
    }

    private static void calculatePopulation() {

        int[] population = new int[5];      // 다섯 개의 선거구의 각 인구수의 합

        // 1번 선거구
        for(int i = 1; i < point[1][0]; i++) {
            for(int j = 1; j <= point[0][1]; j++) {
                if(isBorder[i][j]) break;
                population[0] += board[i][j];
            }
        }
        // 2번 선거구
        for(int i = 1; i <= point[2][0]; i++) {
            for(int j = n; j > point[0][1]; j--) {
                if(isBorder[i][j]) break;
                population[1] += board[i][j];
            }
        }
        // 3번 선거구
        for(int i = point[1][0]; i <= n; i++) {
            for(int j = 1; j < point[3][1]; j++) {
                if(isBorder[i][j]) break;
                population[2] += board[i][j];
            }
        }
        // 4번 선거구
        for(int i = point[2][0] + 1; i <= n; i++) {
            for(int j = n; j >= point[3][1]; j--) {
                if(isBorder[i][j]) break;
                population[3] += board[i][j];
            }
        }
        // 5번 선거구
        population[4] = totalPopulation;
        for(int i = 0; i <= 3; i++) {
            population[4] -= population[i];
        }

        Arrays.sort(population);
        ans = Integer.min(ans, population[4] - population[0]);
    }

    private static void checkBorder(int d1, int d2) {

        isBorder = new boolean[n+1][n+1];

        for(int i = 0; i <= d1; i++) {
            isBorder[point[0][0] + i][point[0][1] - i] = true;
            isBorder[point[2][0] + i][point[2][1] - i] = true;
        }

        for(int i = 0; i <= d2; i++) {
            isBorder[point[0][0] + i][point[0][1] + i] = true;
            isBorder[point[1][0] + i][point[1][1] + i] = true;
        }
    }

}
