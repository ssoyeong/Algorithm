import java.io.*;
import java.util.*;

// 수영장

public class SWEA1952 {

	static int tc;
	static int[] cost = new int[4];
	static int[] plan = new int[12];
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			sb.append("#" + t + " " + solution() + "\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int solution() {
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 12; i++) {
			if(plan[i] > 0) {
				queue.add(new int[] {cost[0] * plan[i], i + 1});
				queue.add(new int[] {cost[1], i + 1});
				queue.add(new int[] {cost[2], i + 3});
				queue.add(new int[] {cost[3], i + 12});
				break;
			}
		}
		
		while(!queue.isEmpty()) {
			
			int[] poll = queue.poll();
			if(poll[1] > 11) min = Integer.min(min, poll[0]);
			else if(plan[poll[1]] == 0) queue.add(new int[] {poll[0], poll[1] + 1});
			else {
				int newCost = poll[0] + cost[0] * plan[poll[1]];
				if(newCost < min) queue.add(new int[] {newCost, poll[1] + 1});
				
				newCost = poll[0] + cost[1];
				if(newCost < min) queue.add(new int[] {newCost, poll[1] + 1});
				
				newCost = poll[0] + cost[2];
				if(newCost < min) queue.add(new int[] {newCost, poll[1] + 3});
			}
		}
		
		return min;
	}
}
