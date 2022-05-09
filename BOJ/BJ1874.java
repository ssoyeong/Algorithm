package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;

// 스택 수열

public class BJ1874 {
	
	static int n;
	static int x;
	static int popped;		// 가장 마지막에 pop된 값
	static int pushed;		// 수열 1~n 중 어디까지 push 했는지
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());
			if(x > popped) {
				for(int j = pushed + 1; j <= x; j++) {
					stack.add(j);
					sb.append("+\n");
				}
				pushed = x;
				popped = stack.pop();
			}
			else {
				popped = stack.pop();
				if(popped != x) {
					System.out.println("NO");
					System.exit(0);
				}
			}
			
			sb.append("-\n");
		}
		
		System.out.println(sb.toString());
	}
}
