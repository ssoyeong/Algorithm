import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 요세푸스 문제 0

public class BJ11866 {

    static int n;
    static int k;
    static Queue<Integer> queue = new LinkedList<>();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= n; i++) {
            queue.add(i);
        }

        answer.append("<");
        solution();
        answer.deleteCharAt(answer.length()-1);
        answer.deleteCharAt(answer.length()-1);
        answer.append(">");
        
        System.out.println(answer.toString());
        
    }

    private static void solution() {

        int idx = 1;
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            if(idx % k == 0) {
                answer.append(poll).append(", ");
                idx = 1;
            }
            else {
                queue.add(poll);
                idx++;
            }
        }
    }    
}
