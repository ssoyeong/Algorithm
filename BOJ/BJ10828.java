package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

// 스택

public class BJ10828 {
	
	static int n;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			switch(cmd) {
			case "push": stack.add(Integer.parseInt(st.nextToken())); break;
			case "pop": if(stack.isEmpty()) sb.append("-1\n"); else sb.append(stack.pop()).append("\n"); break;
			case "size": sb.append(stack.size()).append("\n"); break;
			case "empty": if(stack.isEmpty()) sb.append("1\n"); else sb.append("0\n"); break;
			case "top": if(stack.isEmpty()) sb.append("-1\n"); else sb.append(stack.peek()).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
