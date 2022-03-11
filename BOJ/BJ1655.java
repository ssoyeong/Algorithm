package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

// 가운데를 말해요

public class BJ1655 {

	static int n;
	static PriorityQueue<Integer> queueMax = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> queueMin = new PriorityQueue<>();
	static int input;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		// 시간초과 이슈 StringBuilder 사용하니 해결
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			input = Integer.parseInt(br.readLine());
			
			if(queueMax.size() == queueMin.size()) {
				queueMax.add(input);
			}
			else {
				queueMin.add(input);
			}
			
			if(!queueMin.isEmpty() && !queueMax.isEmpty() && queueMax.peek() > queueMin.peek()) {
				queueMin.add(queueMax.poll());
				queueMax.add(queueMin.poll());
			}
			
			sb.append(queueMax.peek()).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
