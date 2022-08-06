import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 등수 구하기

public class BJ1205 {

    static int n;
    static int target;
    static int p;
    static int answer = -1;
    static Integer[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        
        if(n == 0) answer = 1;
        else {
            arr = new Integer[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                arr[i] = x;
            }

            Arrays.sort(arr, Collections.reverseOrder());

            if(n == p && target <= arr[n-1]) answer = -1;
            else {
                int rank = 1;
                for(int i = 0; i < n; i++) {
                    if(arr[i] > target) rank++;
                    else break;
                }
                answer = rank;
            }
        }

        System.out.println(answer);
    }
    
}
