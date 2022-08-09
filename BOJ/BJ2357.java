import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 최솟값과 최댓값

public class BJ2357 {

    static int n, m;
    static int[] arr;
    static int[] minTree;
    static int[] maxTree;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        minTree = new int[4*n];
        maxTree = new int[4*n];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMinTree(1, n, 1);
        initMaxTree(1, n, 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findMin(1, n, 1, a, b) + " " + findMax(1, n, 1, a, b)).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int initMinTree(int start, int end, int idx) {

        if(start == end) {
            return minTree[idx] = arr[start];
        }

        int mid = (start + end) / 2;
        return minTree[idx] = Math.min(initMinTree(start, mid, idx*2), initMinTree(mid+1, end, idx*2+1));
    }

    private static int initMaxTree(int start, int end, int idx) {

        if(start == end) {
            return maxTree[idx] = arr[start];
        }

        int mid = (start + end) / 2;
        return maxTree[idx] = Math.max(initMaxTree(start, mid, idx*2), initMaxTree(mid+1, end, idx*2+1));
    }

    private static int findMin(int start, int end, int idx, int a, int b) {

        if(b < start || end < a) return Integer.MAX_VALUE;
        if(a <= start && end <= b) return minTree[idx];

        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, idx*2, a, b), findMin(mid+1, end, idx*2+1, a, b));
    }
    
    private static int findMax(int start, int end, int idx, int a, int b) {
        
        if(b < start || end < a) return Integer.MIN_VALUE;
        if(a <= start && end <= b) return maxTree[idx];

        int mid = (start + end) / 2;
        return Math.max(findMax(start, mid, idx*2, a, b), findMax(mid+1, end, idx*2+1, a, b));
    }
}
