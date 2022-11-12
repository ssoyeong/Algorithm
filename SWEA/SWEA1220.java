import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

// Magnetic

class SWEA1220 {
	
    static final int SIZE = 100;
    static ArrayList<Integer>[] arr;
    static int cnt;
    
    
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        for(int tc = 1; tc <= 10; tc++) {
        	br.readLine();
            arr = new ArrayList[SIZE];
            for(int i = 0; i < SIZE; i++) {
            	arr[i] = new ArrayList<>();
            }
            cnt = 0;
            
            for(int i = 0; i < SIZE; i++) {
            	st = new StringTokenizer(br.readLine());
                for(int j = 0; j < SIZE; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if(x != 0) arr[j].add(x);
                }
            }
            
            solution();
            sb.append(String.format("#%d %d\n", tc, cnt));
        }
        
        System.out.println(sb.toString());
    }
    
    private static void solution() {
    	
        for(int i = 0; i < SIZE; i++) {
        	StringBuilder sb = new StringBuilder();
            for(int j = 0; j < arr[i].size(); j++) {
                sb.append(arr[i].get(j));
            }
            
            String line = sb.toString();
            
            for(int idx = 0; idx < line.length() - 1; idx++) {
            	if(line.substring(idx, idx+2).equals("12")) cnt++;
            }
        }
    }
}