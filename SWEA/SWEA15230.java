import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 알파벳 공부

public class SWEA15230 {
    
    static int tc;
    static String input;
    static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= tc; t++) {
            cnt = 0;
            input = br.readLine();

            char ans = 'a';
            for(int i = 0; i < input.length(); i++) {
                if(ans == input.charAt(i)) {
                    ans++;
                    cnt++;
                }
                else break;
            }

            sb.append("#" + t + " " + cnt + "\n");
        }

        System.out.println(sb.toString());
    }
}
