import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 수 묶기

public class BJ1744 {

    static int n;
    static int numOfPositive;
    static long ans;
    static int[] arr;
    static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i] > 0) numOfPositive++;
        }

        Arrays.sort(arr);
        for(int x : arr) deque.add(x);

        solution();
        System.out.println(ans);

    }

    private static void solution() {

        for(int i = 0; i < numOfPositive / 2; i++) {
            int a = deque.pollLast();
            int b = deque.pollLast();

            if(a == 1 || b == 1) {
                ans += a;
                ans += b;
            }
            else {
                ans += a * b;
            }
        }

        if(numOfPositive % 2 == 1) {
            ans += deque.pollLast();
        }

        for(int i = 0; i < (n - numOfPositive) / 2; i++) {
            int a = deque.pollFirst();
            int b = deque.pollFirst();

            ans += a * b;
        }

        while(!deque.isEmpty()) {
            ans += deque.pollFirst();
        }
    }

    
}
