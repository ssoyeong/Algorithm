package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;

// 제로

public class BJ10773 {
	
	static int k;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < k; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0) stack.pop();
			else stack.add(x);
		}
		
		long answer = 0;
		while(!stack.isEmpty()) {
			
			int pop = stack.pop();
			answer += pop;
		}
		
		System.out.println(answer);
	}

}
