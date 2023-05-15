import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

// 원재의 메모리 복구하기

public class SWEA1289 {

    static int t;
    static Queue<Character> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {

            String line = br.readLine();
            for(int i = 0; i < line.length(); i++) {
                queue.add(line.charAt(i));
            }

            int ans = solution();
            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {

        int cnt = 0;
        Character pre = '0';
        while(!queue.isEmpty()) {

            Character poll = queue.poll();

            if(pre != poll) {
                cnt++;
                pre = poll;
            }
        }

        return cnt;
    }
    
}
