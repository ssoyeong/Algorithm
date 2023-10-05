import java.io.*;
import java.util.*;

// [모의 SW 역량테스트] 벽돌 깨기

public class SWEA5656 {
	
	static int n, w, h;
	static int min;
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int[][] map = new int[h][w];
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			solution(0, map);
			sb.append("#" + t + " " + min + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void solution(int depth, int[][] map) {		// depth번째에 어느 위치에서 구슬을 쏠지 정하기
		
		if(depth == n) {
			int cnt = 0;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] != 0) cnt++;
				}
			}
			
			min = Integer.min(min, cnt);
			return;
		}
		
		for(int i = 0; i < w; i++) {
			int[][] tmp = new int[h][w];
			for(int r = 0; r < h; r++) {
				for(int c = 0; c < w; c++) {
					tmp[r][c] = map[r][c];
				}
			}
			
			shooting(i, tmp);		// i 위치에서 구슬 쏘기
			doGravity(tmp);			// 벽돌을 아래로 떨어뜨리기
			solution(depth + 1, tmp);
		}
	}
	
	private static void shooting(int c, int[][] map) {
		
		int r = 0;
		for(r = 0; r < h; r++) {
			if(map[r][c] != 0) break;
		}
		
		if(r == h) return;
		
		// (r, c)에 위치한 벽돌 명중하기
		queue.add(new int[] {r, c, map[r][c]});
		map[r][c] = 0;
		
		while(!queue.isEmpty()) {
			
			int[] poll = queue.poll();
			for(int d = 0; d < 4; d++) {
				
				int xx = poll[0];
				int yy = poll[1];
				int dist = 0;
				while(true) {
					xx += dx[d];
					yy += dy[d];
					if(xx < 0 || xx >= h || yy < 0 || yy >= w) break;
					if(++dist == poll[2]) break;
					
					if(map[xx][yy] > 1) queue.add(new int[] {xx, yy, map[xx][yy]});
					map[xx][yy] = 0;
				}
			}
		}
	}
	
	private static void doGravity(int[][] map) {
		
		for(int j = 0; j < w; j++) {
			int floor = h -1;
			for(int i = h - 1; i >= 0; i--) {
				if(map[i][j] != 0) {
					map[floor--][j] = map[i][j];
					if(i != floor + 1) map[i][j] = 0;
				}
			}
		}
	}
}
