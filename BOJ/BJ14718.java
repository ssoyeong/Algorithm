import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 빗물

public class BJ14718 {

    static int h, w;
    static int[] arr;
    static int total;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new int[w];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        System.out.println(total);
    }

    private static void solution() {

        for(int i = 0; i < w; i++) {

            int leftMax = arr[0];
            int current = arr[i];
            int rightMax = arr[w - 1];

            for(int l = 1; l < i; l++) {
                leftMax = Integer.max(leftMax, arr[l]);
            }

            for(int r = w - 2; r > i; r--) {
                rightMax = Integer.max(rightMax, arr[r]);
            }

            if(leftMax > current && rightMax > current) {
                total += Integer.min(leftMax, rightMax) - current;
            }
        }
    }
    
}
