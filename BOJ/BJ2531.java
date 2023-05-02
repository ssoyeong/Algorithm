import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

// 회전초밥

public class BJ2531 {

    static int n, d, k, c;
    static int ans;
    static int[] sushi;
    static HashMap<Integer, Integer> set = new HashMap<>();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushi = new int[n];

        for(int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        solution();
        System.out.println(ans);
    }

    private static void solution() {

        set.put(c, 1);

        for(int i = 0; i < n+k; i++) {

            if(i < k) {
                insert(sushi[i]);
            }
            else if(k <= i && i < n) {
                insert(sushi[i]);
                remove(sushi[i-k]);
            }
            else {
                insert(sushi[i-n]);
                remove(sushi[i-k]);
            }
            ans = Integer.max(ans, set.size());
        }
    }

    private static void insert(int input) {
        if(set.containsKey(input)) {
            int cnt = set.get(input);
            set.put(input, cnt + 1);
        }
        else {
            set.put(input, 1);
        }
    }

    private static void remove(int input) {
        int cnt = set.get(input);
        if(cnt == 1) {
            set.remove(input);
        }
        else {
            set.put(input, cnt - 1);
        }
    }
}
