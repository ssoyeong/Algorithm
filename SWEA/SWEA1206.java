import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S/W 문제해결 기본] 1일차 - View

class SWEA1206 {
	
    static int n;
    static int[] arr;
    static int start, mid, end;
    static int max;
    static int total;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        for(int tc = 1; tc <= 10; tc++) {
        	
            start = 0;
            mid = 2;
            end = 4;
            max = 0;
            total = 0;
            
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
         	st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            solution();
			sb.append(String.format("#%d %d\n", tc, total));
        }
        
        System.out.println(sb.toString());
    }
    
    private static void solution() {
    	
        for(int i = 0; i < n-4; i++) {
        	max = 0;
            for(int idx = start; idx <= end; idx++) {
            	if(idx == mid) continue;
                if(arr[idx] > max) max = arr[idx];
            }
            
            if(arr[mid] > max) total += arr[mid] - max;
            start++;
            mid++;
            end++;
        }
    }
}