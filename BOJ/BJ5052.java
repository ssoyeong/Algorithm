import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

// 전화번호 목록

public class BJ5052 {

    static int t, n;
    static String[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        for(int test = 0; test < t; test++) {
            n = Integer.parseInt(br.readLine());
            arr = new String[n];

            for(int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);
            solution();
        }
       
        System.out.println(sb.toString());
    }

   private static void solution() {

        for(int i = 0; i < n-1; i++) {
            if(arr[i+1].startsWith(arr[i])) {
                sb.append("NO\n");
                return;
            }
        }

        sb.append("YES\n");
   }
}
