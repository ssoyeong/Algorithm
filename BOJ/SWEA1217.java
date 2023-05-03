import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// [S/W 문제해결 기본] 4일차 - 거듭 제곱

public class SWEA1217 {

    static int base, exponent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int result;

        for(int i = 1; i <= 10; i++) {
            int tc = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            base = Integer.parseInt(st.nextToken());
            exponent = Integer.parseInt(st.nextToken());

            if(exponent == 0) result = 1;
            else result = recursion(base, exponent);
            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int recursion(int num, int times) {
        if(times == 1) return num;
        return recursion(num * base, times - 1);
    }

}