import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

// 나는야 포켓몬 마스터 이다솜

public class BJ1620 {

    static int n, m;
    static HashMap<Integer, String> numToName = new HashMap<>();
    static HashMap<String, Integer> nameToNum = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= n; i++) {
            String input = br.readLine();
            numToName.put(i, input);
            nameToNum.put(input, i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            String input = br.readLine();
            if(49 <= input.charAt(0) && input.charAt(0) <= 57) {
                sb.append(numToName.get(Integer.parseInt(input))).append("\n");
            }
            else {
                sb.append(nameToNum.get(input)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
    
    
}
