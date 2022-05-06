package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

// 최대 힙

public class BJ11279 {
	
	static int n;
	static int x;
	static PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());
			solution(x);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void solution(int x) {
		
		if(x == 0) {
			if(queue.isEmpty()) {
				sb.append(0).append("\n");
			}
			else {
				sb.append(queue.poll()).append("\n");
			}
		}
		else {
			queue.add(x);
		}
	}
}
