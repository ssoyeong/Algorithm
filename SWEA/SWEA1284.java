import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수도 요금 경쟁

class SWEA1284 {
	
    static int t;
    static int p, q, r, s, w;
 	static int cost_a;
    static int cost_b;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= t; tc++) {
            
            cost_a = 0;
            cost_b = 0;
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            
            cost_a = p * w;
            if(r >= w) cost_b = q;
            else {
            	cost_b += q;
                cost_b += (w - r) * s;
            }
            
            if(cost_a > cost_b) sb.append(String.format("#%d %d\n", tc, cost_b));
            else sb.append(String.format("#%d %d\n", tc, cost_a));
        }
        
        System.out.println(sb.toString());
    }
}