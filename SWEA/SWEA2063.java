import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2063 {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        System.out.println(arr[n / 2]);
    }
    
}
