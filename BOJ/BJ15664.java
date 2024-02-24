import java.io.*;
import java.util.*;

// N과 M (10)

public class BJ15664 {
    
    static int n, m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        backTracking(0, 0, "", 0);
        System.out.println(sb.toString());
    }

    private static void backTracking(int depth, int idx, String ans, int num) {

        if(depth == m) {
            sb.append(ans + "\n");
            return;
        }

        int pre = 0;
        for(int i = idx; i < n; i++) {

            int newNum = num + (int)Math.pow(10, depth) * arr[i];
            if(newNum <= pre) continue;
            pre = newNum;

            String newAns = ans.concat(String.valueOf(arr[i])).concat(" ");
            backTracking(depth + 1, i + 1, newAns, newNum);
        }
    }
}
