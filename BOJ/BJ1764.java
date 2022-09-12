import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// 듣보잡

public class BJ1764 {

    private static int n, m;
    private static HashSet<String> set = new HashSet<>();
    private static ArrayList<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        for(int i = 0; i < m; i++) {
            String input = br.readLine();
            if(set.contains(input)) {
                answer.add(input);
            }
        }

        Collections.sort(answer);
        
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size() + "\n");
        for(String str : answer) {
            sb.append(str + "\n");
        }
        System.out.println(sb.toString());

    }
    
}
