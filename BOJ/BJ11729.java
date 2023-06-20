import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 하노이 탑 이동 순서

public class BJ11729 {
    
    static int n;
    static int k;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = (int)Math.pow(2, n) - 1;

        sb.append(k + "\n");
        hanoi(n, 1, 3, 2);
        System.out.println(sb.toString());
    }

    private static void hanoi(int num, int start, int end, int sub) {

        if(num == 1) {
            move(1, start, end);
            return;
        }

        hanoi(num - 1, start, sub, end);
        move(num, start, end);
        hanoi(num - 1, sub, end, start);
    }

    private static void move(int num, int start, int end) {
        // sb.append(num + "번 원반을 " + start + " " + end + "\n");
        sb.append(start + " " + end + "\n");
    }
}   
