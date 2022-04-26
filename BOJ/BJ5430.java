package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// AC

public class BJ5430 {
	
	static int t;
	static String cmd;
	static int n;
	static Deque<Integer> deque = new ArrayDeque<>();
	static boolean head;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());
		
		for(int test = 0; test < t; test++) {
			
			deque.clear();
			head = true;
			
			cmd = br.readLine();
			n = Integer.parseInt(br.readLine());
			String input = br.readLine();
			if(n == 0) input = "";
			else {
				input = input.substring(1, input.length() - 1);
			}
			
			st = new StringTokenizer(input, ",");
			
			while(st.hasMoreElements()) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			
			if(solution()) sb.append("error\n");
			else print();
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean solution() {
		
		for(int i = 0; i < cmd.length(); i++) {
			if(cmd.charAt(i) == 'R') {
				if(head) head = false;
				else head = true;
			}
			else {
				if(deque.isEmpty()) return true;	// deque가 비어 있는데 삭제하는 경우, true 반환
				
				if(head) deque.removeFirst();		// head == true라면, head부터 삭제
				else deque.removeLast();			// head == false라면, tail부터 삭제
			}
		}
		
		return false;
	}
	
	private static void print() {
		
		sb.append("[");
		
		if(deque.size() != 0) {		// deque가 비어있다면 "[]"만 출력하도록
			if(head) {				// head == true라면, deque 순서대로 출력
				while(!deque.isEmpty()) {
					sb.append(deque.pollFirst()).append(",");
				}
			}
			else {					// head == false라면, deque 거꾸로 출력
				while(!deque.isEmpty()) {
					sb.append(deque.pollLast()).append(",");
				}
			}
			
			sb.deleteCharAt(sb.length()-1);		// 마지막 추가된 "," 삭제
		}
		
		sb.append("]\n");
	}

}
