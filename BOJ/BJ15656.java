import java.io.*;
import java.util.*;

// N과 M (7)

public class BJ15656 {
    static int n, m;
    static int[] arr;
    static int[] output;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        output = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        backTracking(0);
        System.out.println(sb.toString());
    }

    private static void backTracking(int depth) {

        if(depth == m) {
            for(int i = 0; i < m; i++) {
                sb.append(output[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < n; i++) {
            output[depth] = arr[i];
            backTracking(depth + 1);
        }
    }
}