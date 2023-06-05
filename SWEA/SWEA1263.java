import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// [S/W 문제해결 응용] 8일차 - 사람 네트워크2

public class SWEA1263 {
    
    static int tc;
    static int n;
    static int[] cc;
    static int[][] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            cc = new int[n+1];
            adj = new int[n+1][n+1];
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(i == j) {
                        adj[i][j] = 0;
                    }
                    else {
                        adj[i][j] = 99999;
                    }
                }
            }

            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(st.nextToken().equals("1")) {
                        adj[i][j] = 1;
                    }
                }
            }

            int ans = solution();
            sb.append("#" + t + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {

        // i부터 j까지의 최단 거리 구하기
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(adj[i][j] == 0 || adj[i][j] == 1) continue;
                    adj[i][j] = Integer.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        // CC 값 계산
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                cc[i] += adj[i][j];
            }
        }

        Arrays.sort(cc);
        return cc[1];
    }

}
