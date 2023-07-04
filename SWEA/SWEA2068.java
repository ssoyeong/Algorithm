import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2068 {

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

            Arrays.sort(arr);
            sb.append("#" + tc + " " + arr[9] + "\n");
        }

        System.out.println(sb.toString());
    }
    
}
