import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

// 백만 장자 프로젝트

class SWEA1859
{
    static int t;
    static int n;
    static int max;
	static long total;
    static Stack<Integer> stack = new Stack<Integer>();
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int testCase = 0; testCase < t; testCase++) {
        	n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                stack.add(x);
            }
            
            solution();
            sb.append(String.format("#%d %d\n", testCase+1, total));
        }
        
        System.out.println(sb);
	}
    
    private static void solution() {
        
		max = 0;
        total = 0;
        
        while(!stack.isEmpty()) {
            int pop = stack.pop();
            if(pop > max) max = pop;
            
            total += max - pop;
        }
    }
}