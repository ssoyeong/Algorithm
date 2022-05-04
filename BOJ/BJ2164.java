package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

// 카드2

public class BJ2164 {
	
	static int n;
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= n; i++) {
			queue.add(i);
		}
		
		
		while(true) {
			if(queue.size() == 1) break;
			queue.poll();
			if(queue.size() == 1) break;
			int poll = queue.poll();
			queue.add(poll);
		}
		
		System.out.println(queue.poll());
	}

}
