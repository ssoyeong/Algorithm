package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 순회강연

public class BJ2109 {

	static class Lecture implements Comparable<Lecture>{
		
		int day;
		int fee;
		
		Lecture(int fee, int day){
			this.day = day;
			this.fee = fee;
		}

		@Override
		public int compareTo(Lecture o) {
			if(this.fee == o.fee) return this.day - o.day;
			else return o.fee - this.fee;
		}
	
	}
	
	static int n;
	static PriorityQueue<Lecture> queue = new PriorityQueue<>();
	static int total;
	static boolean[] visited = new boolean[10001];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			queue.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
		}

		while(!queue.isEmpty()) {
			Lecture poll = queue.poll();
			for(int i = poll.day; i >= 1; i--) {
				if(visited[i] == false) {
					visited[i] = true;
					total += poll.fee;
					break;
				}
			}
		}
		
		System.out.println(total);
	}
}
