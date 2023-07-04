import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA1959 {

    static int t;
    static int n, m;
    static int[] a;
    static int[] b;
    static int max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {

            max = Integer.MIN_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a = new int[n];
            b = new int[m];
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            if(n <= m) solution(a, b, n, m);
            else solution(b, a, m, n);
            sb.append("#" + tc + " " + max + "\n");
        }

        System.out.println(sb.toString());
    }
    
    private static void solution(int[] arr1, int[] arr2, int length1, int length2) {     // arr1 길이 <= arr2 길이       

        int time = length2 - length1 + 1;

        for(int i = 0; i < time; i++) {
            
            int total = 0;
            for(int j = 0; j < length1; j++) {
                total += arr1[j] * arr2[j + i];
            }
            max = Integer.max(max, total);
        }
    }
}
