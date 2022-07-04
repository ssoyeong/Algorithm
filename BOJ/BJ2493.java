package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

// 탑

public class BJ2493 {
	
	static class Top {
		int idx;
		int height;
		
		Top(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
	static int n;
	static Stack<Top> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= n; i++) {
			
			Top temp = new Top(i, Integer.parseInt(st.nextToken()));
			
			if(stack.isEmpty()) {
				sb.append("0 ");
				stack.add(temp);
			}
			else {
				
				boolean flag = false;
				while(!stack.isEmpty()) {
					Top peek = stack.peek();
					
					if(peek.height > temp.height) {
						sb.append(peek.idx).append(" ");
						stack.add(temp);
						flag = true;
						break;
					}
					else {
						stack.pop();
					}
				}
				
				if(!flag) sb.append("0 ");
				stack.add(temp);
			}
		}
		
		System.out.println(sb.toString());
	}
}
