import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

// 두 용액

public class BJ2470 {

    static int n;
    static int start, end;
    static int[] arr;
    static int diffMin = Integer.MAX_VALUE;
    static int a, b;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        System.out.println(a + " " + b);
    }

    private static void solution() {

        Arrays.sort(arr);
        start = 0;
        end = n - 1;
        int diff = 0;

        while(start != end) {

            diff = arr[start] + arr[end];

            if(diffMin > Math.abs(diff)) {
                diffMin = Math.abs(diff);
                a = arr[start];
                b = arr[end];
            }
            
            if(diff == 0) return;
            if(diff > 0) {
                end--;
            }
            else {
                start++;
            }
        }
    }
    
}
