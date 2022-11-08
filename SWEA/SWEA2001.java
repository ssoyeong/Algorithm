import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파리 퇴치

class SWEA2001 {
    
    static int t;
    static int n, m;
    static int[][] arr;
    static int max;
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; tc++) {
        	
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            max = 0;
            
            for(int i = 0; i < n; i++) {
            	st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                	arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            solution();
           
            sb.append(String.format("#%d %d\n", (tc+1), max));
        }
        
        System.out.println(sb.toString()); 
    }
    
    private static void solution() {
    	
 
        for(int i = 0; i < n - m + 1; i++) {
        	for(int j = 0; j < n - m + 1; j++) {
            	int result = calculate(i, j);
                if(max < result) max = result;
            }
        }
    }
    
    private static int calculate(int x, int y) {
    	
        int total = 0;
        for(int i = x; i < x + m; i++) {
            for(int j = y; j < y + m; j++) {
            	total += arr[i][j];
            }
        }
        
        return total;
    }
}