import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;

// 회전하는 큐

public class BJ1021 {

    static int n, m;
    static int ans;
    static Queue<Integer> targets = new LinkedList<>();
    static Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            targets.add(Integer.parseInt(st.nextToken()));
        }

        solution();
        System.out.println(ans);
    }

    private static void solution() {

        // deque에 1부터 n까지의 수 넣기
        for(int i = 1; i <= n; i++) {
            deque.add(i);
        }

        while(!targets.isEmpty()) {
            int target = targets.poll();

            int cnt = 0;
            int size = deque.size();

            // target이 나올 때까지 앞에서 뽑고 뒤에 넣기
            while(true) {
                int poll = deque.poll();

                if(poll == target) {
                    if(size / 2 >= cnt) {
                        ans += cnt;
                    }
                    else {
                        ans += (size - cnt);
                    }
                    break;
                }
                
                deque.addLast(poll);
                cnt++;
            }
        }
    }
    
}
