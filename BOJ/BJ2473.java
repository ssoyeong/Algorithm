import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 세 용액

public class BJ2473 {

    static int n;
    static long total = Long.MAX_VALUE;
    static int[] arr;
    static PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            sb.append(queue.poll() + " ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void solution() {

        Arrays.sort(arr);

        for(int i = 0; i < n; i++) {
            int target = arr[i];
            int start = 0;
            int end = n-1;

            while(true) {

                if(arr[start] == target) start++;
                if(arr[end] == target) end--;
                if(start == end) break;

                long sum = (long)target + (long)arr[start] + (long)arr[end];
                if(Math.abs(total) > Math.abs(sum)) {
                    total = sum;
                    queue.clear();
                    queue.add(target);
                    queue.add(arr[start]);
                    queue.add(arr[end]);
                    if(total == 0) return;
                }

                if(sum > 0 && end > 0) end--;
                else if(sum < 0 && start < n-1) start++;
            }
        }
    }
    
}
