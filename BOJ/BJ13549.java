package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 숨바꼭질 4

public class BJ13549 {
	
	static int n;
	static int k;
	static boolean[] visited = new boolean[100001];
	static LinkedList<int[]> queue = new LinkedList<int[]>();
	static int pos;
	static int sec;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		queue.add(new int[] {n, 0});
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			pos = poll[0];		
			sec = poll[1];
			visited[pos] = true;
			
			if(pos == k) break;
			
			if(pos*2 <= k+1 && !visited[pos*2]) {
				queue.addFirst(new int[] {pos*2, sec});		// 가중치가 0인 경우 우선순위
			}
			if(pos-1 >= 0 && !visited[pos-1]) {
				queue.add(new int[] {pos-1, sec+1});
			}
			if(pos+1 <= k+1 && pos+1 <= k && !visited[pos+1]) {
				queue.add(new int[] {pos+1, sec+1});
			}	
			
		}

		System.out.println(sec);
		
	}
	
}
