import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.IOException;

// 가능한 시험 점수

public class SWEA3752 {

    static int t;
    static int n;
    static int[] scores;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {
            set.clear();
            n = Integer.parseInt(br.readLine());
            scores = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }

            int ans = solution();
            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {

        set.add(0);

       for(int i = 0; i < n; i++) {
            int target = scores[i];
            HashSet<Integer> temp = new HashSet<>();

            temp.add(target);
            temp.addAll(set);
            for(int x : set) {
                temp.add(target + x);
            }

            set.clear();
            set.addAll(temp);
       }

        return set.size();
    }
    
}
