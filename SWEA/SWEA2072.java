import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA2072 {

    static int t;
    static int[] arr = new int[10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < 10; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int ans = solution();
            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }
    
    private static int solution() {

        int total = 0;

        for(int i = 0; i < 10; i++) {
            if(arr[i] % 2 == 1) {
                total += arr[i];
            }
        }

        return total;
    }
}
