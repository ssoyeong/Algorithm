import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 용액

public class BJ2467 {

    static int n;
    static int start, end;
    static int value = Integer.MAX_VALUE;
    static int[] arr;
    static int[] answer = new int[2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static void solution() {

        start = 0;
        end = n-1;

        int sum = 0;
        while(start != end) {

            sum = arr[start] + arr[end];
          
            if(Math.abs(sum) < value) {
                value = Math.abs(sum);
                answer[0] = arr[start];
                answer[1] = arr[end];
                
                if(value == 0) return;
            }

            if(sum > 0) end--;
            else start++;
        }
    }
    
}
