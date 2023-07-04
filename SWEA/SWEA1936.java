import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SWEA1936 {

    static int a, b;
    static boolean flag;        // A가 이기면 true, B가 이기면 false

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        flag = solution();
        if(flag) {
            System.out.println("A");
        }
        else {
            System.out.println("B");
        }
    }

    private static boolean solution() {

        if(a == 1 && b == 3) {
            return true;
        }
        else if(a == 3 && b == 1) {
            return false;
        }
        else {
            if(a > b) return true;
            else return false;
        }
    }
    
}
