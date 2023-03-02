import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 팰린드롬수

public class BJ1259 {

    static String input;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {

            input = br.readLine();
            if(input.equals("0")) break;

            if(solution()) {
                System.out.println("yes");
            }
            else {
                System.out.println("no");
            }
        }
    }

    private static boolean solution() {

        StringBuilder sb = new StringBuilder();
        sb.append(input);
        if(input.equals(sb.reverse().toString())) return true;
        return false;
    }
}