import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 경로 찾기

public class BJ11403 {

    static int n;
    static int[][] matrix;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];

        
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                if(st.nextToken().equals("1")) {
                    matrix[i][j] = 1;
                }
            }
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(matrix[i][j] + " ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }

    private static void floydWarshall() {

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                if(matrix[i][k] == 1) {
                    for(int j = 0; j < n; j++) {
                        if(matrix[k][j] == 1) {
                            matrix[i][j] = 1;
                        }
                    }
                }
            }
        }
    }
    
}
