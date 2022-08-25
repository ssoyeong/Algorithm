import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

// 최소 힙

public class BJ1927 {
    
    static int n, x;
    static PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            x = Integer.parseInt(br.readLine());

            if(x == 0) {
                if(queue.isEmpty()) {
                    sb.append("0\n");
                }
                else {
                    sb.append(queue.poll() + "\n");
                }
            }
            else {
                queue.add(x);
            }
        }

        System.out.println(sb.toString());
    }
    
}
