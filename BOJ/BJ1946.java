import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 신입 사원

public class BJ1946 {

    private static class Person implements Comparable<Person> {

        int document;
        int interview;

        Person(int document, int interview) {
            this.document = document;
            this.interview = interview;
        }

        @Override
        public int compareTo(Person o) {
            return this.document - o.document;
        }
    }
    static int t;
    static int n;
    static PriorityQueue<Person> queue = new PriorityQueue<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 0; tc < t; tc++) {
            queue.clear();
            n = Integer.parseInt(br.readLine());
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                queue.add(new Person(a,b));
            }

            int ans = solution();
            sb.append(ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {

        int cnt = 0;
        int minOfInterview = n+1;

        while(!queue.isEmpty()) {
            Person poll = queue.poll();
            if(minOfInterview > poll.interview) {
                cnt++;
                minOfInterview = poll.interview;
            }
        }
        return cnt;
    }
}
