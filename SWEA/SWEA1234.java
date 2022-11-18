package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

// 비밀번호 - 테케 이상 

public class SWEA1234 {
	
	static int n;
	static Stack<Character> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			String input = st.nextToken();
			
			stack.add(input.charAt(0));
			char pre = input.charAt(0);
			for(int i = 1; i < n; i++) {
				
				if(pre == input.charAt(i)) {
					
					if(!stack.isEmpty()) stack.pop();
					if(!stack.isEmpty()) pre = stack.peek();
				}
				else {
					stack.add(input.charAt(i));
					pre = input.charAt(i);
				}
			}
			
			StringBuilder output = new StringBuilder();
			while(!stack.isEmpty()) {
				output.append(stack.pop());
			}
			
			sb.append(String.format("#" + tc + " " + output.reverse().toString())).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
