import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 두 수의 합

public class BJ3273 {
    static int n;
    static int x;
    static int start, end;
    static int cnt;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        start = 0;
        end = n - 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        x = Integer.parseInt(br.readLine());

        solution();
        System.out.println(cnt);
    }

    private static void solution() {

        Arrays.sort(arr);

        while(true) {
            if(start == end) break;

            if((arr[start] + arr[end]) == x) {
                cnt++;
                end--;
            }
            else if((arr[start] + arr[end]) < x) {
                start++;
            }
            else {
                end--;
            }
        }
    }
}
