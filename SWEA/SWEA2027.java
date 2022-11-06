import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 홀수만 더하기

class SWEA2027
{
   
    static int t;
    static long total;
    
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            total = 0;
            
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
            	int x = Integer.parseInt(st.nextToken());
                if(x%2 == 1) total += x;
            }
            
            sb.append(String.format("#%d %d\n", i+1, total));
        }
        
        System.out.println(sb);
	}
}