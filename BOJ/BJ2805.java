import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 나무 자르기

public class BJ2805 {
    
    static int n, m;
    static int ans;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        binarySearch(1, arr[n-1]);
        System.out.println(ans);
    }

    private static void binarySearch(int low, int high) {

        int mid = (low + high) / 2;

        long sum = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] - mid > 0) {
                sum += arr[i] - mid;
            }
        }

        if(sum == m || ans == mid) {
            ans = mid;
            return;
        }
        else if(sum < m) {
            binarySearch(low, mid - 1);
        }
        else {
            if(mid > ans) ans = mid;
            binarySearch(mid + 1, high);
        }

    }
}
