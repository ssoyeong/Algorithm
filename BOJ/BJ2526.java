import java.io.*;

// 최댓값

public class BJ2526 {
    
    static final int N = 9;

    public static void main(String[] args) throws Exception {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        int max = num;
        int idx = 0;
        int maxIdx = idx;

        while(++idx <= N) {
            num = Integer.parseInt(br.readLine());

            if(max < num) {
                max = num;
                maxIdx = idx;
            }
        }

        System.out.println(max + "\n" + maxIdx);
    }
}
