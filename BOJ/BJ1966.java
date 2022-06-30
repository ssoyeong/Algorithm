package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;
import java.util.StringTokenizer;

// 프린터 큐

public class BJ1966 {
	
	static class Docu implements Comparable<Docu>{
		
		int idx;
		int value;
		
		Docu(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
		
		@Override
		public int compareTo(Docu o) {
			return o.value - this.value;
		}
		
		@Override
		public boolean equals(Object o) {
			
			Docu d = (Docu) o;
			if(this.value == d.value) return true;
			return false;
		}
	}
	
	static int test;
	static int n;
	static int m;
	static int ans;
	static Queue<Docu> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		test = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < test; i++) {
			
			queue.clear();
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				queue.add(new Docu(j, Integer.parseInt(st.nextToken())));
			}
			
			solution();
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void solution() {
		
		while(true) {
			
			Docu poll = queue.poll();
			if(isHighest(poll)) {
				ans++;
				if(poll.idx == m) break;
			}
			else {
				queue.add(poll);
			}
		}
	}
	
	private static boolean isHighest(Docu target) {
		
		if(target.value == 9) return true;
		
		for(int i = target.value +1; i <= 9; i++) {
			Docu temp = new Docu(-1, i);
			if(queue.contains(temp)) return false;
		}
		
		return true;
	}
}
