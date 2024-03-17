import java.io.*;
import java.util.*;

// IPv6

public class BJ3107 {

    static final int LENGTH = 8;
    static int cnt;
    
    public static void main(String[] args) throws Exception {

        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int pos = input.indexOf("::");

        if(pos == -1) {     // double colon not exists
            answer.append(convert(input));
        }
        else {              // double colon exists
            String pre = convert(input.substring(0, pos));
            String post = convert(input.substring(pos, input.length()));
            String zeros = "";
            for(int i = 0; i < LENGTH - cnt; i++) {
                zeros = zeros.concat("0:");
            }
            zeros = convert(zeros);
            answer.append(pre + zeros + post);
        }

        // delete last colon
        answer = answer.deleteCharAt(answer.length() - 1);
        System.out.println(answer.toString());
    }

    private static String convert(String input) {

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(input, ":");
        while(st.hasMoreTokens()) {
            String arr = st.nextToken();
            for(int i = 0; i < (4 - arr.length()); i++) {
                sb.append("0");
            }
            sb.append(arr + ":");
            cnt++;
        }

        return sb.toString();
    }
}
