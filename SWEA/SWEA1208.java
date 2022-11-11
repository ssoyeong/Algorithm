import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

// Flatten

class SWEA1208 {
	
    static int dump;
    static int[] arr;
    
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc =1; tc <= 10; tc++) {
        	
            arr = new int[100];
            dump = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            for(int n = 0; n < dump; n++) {
            	Arrays.sort(arr);
                arr[0]++;
                arr[99]--;
            }
            
            Arrays.sort(arr);
            sb.append(String.format("#%d %d\n", tc, (arr[99]-arr[0])));
        }
        
        System.out.println(sb.toString());
    }

}