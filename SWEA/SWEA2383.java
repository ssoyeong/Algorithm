import java.io.*;
import java.util.*;

public class SWEA2383 {
	
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int ans;
	static int M, S;						// 사람 수, 계단 수
	static Point[] man = new Point[10];		// 사람 위치
	static Point[] stair = new Point[2];	// 계단 위치
	static int[] length = new int[2];		// 계단 길이
	static int[] match;						// 사람별 매칭된 계단
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t = 1; t <= tc; t++) {
			
			ans = Integer.MAX_VALUE;
			M = 0;
			S = 0;
			
			int n = Integer.parseInt(br.readLine());
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					int x = Integer.parseInt(st.nextToken());
					if(x == 1) {
						man[M++] = new Point(i, j);
					}
					else if(x > 1) {
						stair[S] = new Point(i, j);
						length[S++] = x;
					}
				}
			}
			
			match = new int[M];
			dfs(0);
			sb.append("#" + t + " " + ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int depth) {		// 중복순열로 사람별 계단 매칭하기
		
		if(depth == M) {
			ans = Integer.min(ans, solution());
			return;
		}
		
		match[depth] = 0;
		dfs(depth + 1);
		match[depth] = 1;
		dfs(depth + 1);
	}
	
	private static int solution() {		// match[]에 저장된 조합으로 걸리는 시간 구하기
		
		PriorityQueue<Integer>[] queue = new PriorityQueue[2];		// 0번 계단, 1번 계단
		for(int i = 0; i < 2; i++) {
			queue[i] = new PriorityQueue<>();
		}
		
		for(int i = 0; i < M; i++) {
			int dist = Math.abs(man[i].x - stair[match[i]].x) + Math.abs(man[i].y - stair[match[i]].y);
			queue[match[i]].add(dist);		// 각 계단에 매칭되는 사람들의 거리 넣기
		}
		
		int rM = M;							// 남은 사람 수
		int[][] rTime = new int[2][3];		// 각 계단(최대 3명)을 이용하는 사람들의 남은 이용 시간
		int time = 0;
		
		while(true) {
			
			if(rM == 0) {					// 남아있는 사람이 없다면, rTime[][] 값이 모두 0이 되었는지 확인하기
				boolean flag = true;
				for(int i = 0; i < 3; i++) {
					for(int n = 0; n < 2; n++) {
						if(rTime[n][i] != 0) {
							flag = false;
							break;
						}
					}
				}
				
				if(flag) break;				// rTime[][] 값이 모두 0이라면, while문 종료하기
			}
			
			for(int i = 0; i < 3; i++) {				// 최대 3명에 대해
				for(int n = 0; n < 2; n++) {			// 각 계단에 대해
					
					if(rTime[n][i] > 0) {				// 현재 이용하는 사람이 있다면
						rTime[n][i]--;							// 시간을 감소하기
					}
					
					if(rTime[n][i] == 0) {				// 현재 이용하는 사람이 없다면
						if(!queue[n].isEmpty()) {				// 계단에 올 사람이 남아있다면
							if(queue[n].peek() <= time) {		// 그 사람이 도착했다면
								
								queue[n].poll();
								rM--;
								rTime[n][i] = length[n];		// 해당 계단의 길이만큼을 줌	
							}
						}
					}
				}
			}
			time++;
		}
		
		return time;
	}
}
