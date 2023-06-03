import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

// 히스토그램

public class BJ1725 {
    
    static int n;
    static int max;
    static int[] arr;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+2];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solution();
        System.out.println(max);
    }

    private static void solution() {

        stack.add(0);

        for(int i = 1; i < n + 2; i++) {

            while(!stack.isEmpty()) {

                int peek = stack.peek();
                
                if(arr[peek] <= arr[i]) break;
                stack.pop();
                max = Integer.max(max, arr[peek] * (i - stack.peek() - 1));
            }

            stack.add(i);
        }
    }
    
}
