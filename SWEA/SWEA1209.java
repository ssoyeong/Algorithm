import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

// Sum

class SWEA1209 {

	static int[][] arr;
    static int row;
    static long[] col;
    static long cross_a, cross_b;
    static long max_row, max_col, max_cross;
    static long result;
    
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= 10; tc++) {
        	int num = Integer.parseInt(br.readLine());
            arr = new int[101][101];
            col = new long[101];
            cross_a = 0;
            cross_b = 0;
            max_row = 0;
            max_col = 0;
            max_cross = 0;
            result = 0;
            
            for(int i = 1; i <= 100; i++) {
            	st = new StringTokenizer(br.readLine());
                row = 0;
                for(int j = 1; j <= 100; j++) {
                	arr[i][j] = Integer.parseInt(st.nextToken());
                    
                    row += arr[i][j];
                    col[j] += arr[i][j];
                    if(i == j) cross_a += arr[i][j];
                    if((i + j) == 101) cross_b += arr[i][j];
                    
                }
                
                if(max_row < row) max_row = row;
            }
            
            Arrays.sort(col);
            max_col = col[100];
            max_cross = (cross_a > cross_b) ? cross_a : cross_b;
            
            result  = (max_row > max_col) ? max_row : max_col;
            result = (result > max_cross) ? result : max_cross;
            
            sb.append(String.format("#%d %d\n", tc, result));   
        }
                                                                 
        System.out.println(sb.toString());
    }
}