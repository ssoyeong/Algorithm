import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 회문

public class BJ17609 {

    static int t;
    static int front, back;
    static String input;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            input = br.readLine();
            int ans = solution();
            sb.append(ans + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution() {

        int length = input.length();
        for(int i = 0; i < length/2; i++) {
            front = i;
            back = length - i - 1;

            if(input.charAt(front) != input.charAt(back)) {

                String deletedFront = input.substring(0, front).concat(input.substring(front+1, length));
                if(isPalindrome(deletedFront)) return 1;

                String deletedBack = input.substring(0, back).concat(input.substring(back+1, length));
                if(isPalindrome(deletedBack)) return 1;

                return 2;
            }
        }

        return 0;
    }

    private static boolean isPalindrome(String target) {

        StringBuilder reversed = new StringBuilder(target).reverse();
        if(target.equals(reversed.toString())) return true;
        return false;
    }
    
}
