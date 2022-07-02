package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 큐

public class BJ10845 {
	
	static int n;
	static int last;
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if(cmd.equals("push")) {
				int x = Integer.parseInt(st.nextToken());
				queue.add(x);
				last = x;
			}
			else if(cmd.equals("pop")) {
				
				if(queue.size() > 0) sb.append(queue.poll()).append("\n");
				else sb.append("-1\n");
			}
			else if(cmd.equals("size")) {
				sb.append(queue.size()).append("\n");
			}
			else if(cmd.equals("empty")) {
				if(queue.size() > 0) sb.append("0\n");
				else sb.append("1\n");
			}
			else if(cmd.equals("front")) {
				
				if(queue.size() > 0) sb.append(queue.peek()).append("\n");
				else sb.append("-1\n");
			}
			else if(cmd.equals("back")) {
				
				if(queue.size() > 0) {
					sb.append(last).append("\n");
				}
				else sb.append("-1\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
