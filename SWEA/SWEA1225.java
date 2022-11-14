package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 암호생성기 

public class SWEA1225 {
	
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= 10; tc++) {
			
			queue.clear();
			br.readLine();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				int x = Integer.parseInt(st.nextToken());
				queue.add(x);
			}
			
			
			solution();
			sb.append("#" + tc + " ");
			while(!queue.isEmpty()) {
				int poll = queue.poll();
				sb.append(poll + " ");
			}
			
			sb.append("\n");	
		}
		
		System.out.println(sb.toString());
	}
	
	private static void solution() {
		
		while(true) {
			
			for(int i = 1; i <= 5; i++) {
				
				int x = queue.poll();
				if(x - i <= 0) {
					queue.add(0);
					return;
				}
				
				queue.add(x - i);
			}
		}
	}
	
}
