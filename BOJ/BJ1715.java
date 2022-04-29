package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;

// 카드 정렬하기

public class BJ1715 {

	static int n;
	static PriorityQueue<Integer> queue = new PriorityQueue<>();
	static int total;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			queue.add(Integer.parseInt(br.readLine()));
		}
		
		while(queue.size() > 1) {	
			int a = queue.poll();
			int b = queue.poll();
			total += a + b;			// 가장 작은 두 수 뽑아서 더하고
			queue.add(a + b);		// 더한 수 다시 삽입
		}
		
		System.out.println(total);
	}
}
