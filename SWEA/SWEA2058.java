import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SWEA2058 {

    static int n;
    static int length;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        length = input.length();
        n = Integer.parseInt(input);
        
        int total = solution();
        System.out.println(total);
    }

    private static int solution() {

        int total = 0;

        for(int i = 0; i < length; i++) {

            total += n % 10;
            n = n / 10;
        }
        
        return total;
    }
    
}
