package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;
import java.util.Comparator;

// 절댓값 힙

public class BJ11286 {

	static int n;
	
	static PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
		
		@Override
		public int compare(Integer o1, Integer o2) {
			
			if(Math.abs(o1) == Math.abs(o2)) {
				return o1 - o2;
			}
			if(Math.abs(o1) < Math.abs(o2)) return -1;
			return 1;
		}
	});
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(queue.isEmpty()) sb.append(0).append("\n");
				else {
					int poll = queue.poll();
					sb.append(poll).append("\n");
				}
			}
			else {
				queue.add(x);
			}
		}
		
		System.out.println(sb.toString());
	}
}
