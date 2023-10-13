import java.io.*;
import java.util.*;

// 마법사 상어와 복제

public class BJ23290_2 {

	static class Fish {
		int x;
		int y;
		int d;
		
		Fish(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	static int m, s;
	static Fish shark;
	static int[][] visited = new int[4][4];
	static ArrayList<Integer>[][] map = new ArrayList[4][4];
	static ArrayList<Integer>[][] tmp = new ArrayList[4][4];
	static int[][] smell = new int[4][4];
	static ArrayDeque<Fish> copied = new ArrayDeque<>();
	// pos: 제외되는 물고기 수, 사전 순 이동 방법 담음
	static PriorityQueue<int[]> pos = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o2[0], o1[0]));
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx2 = {-1, 0, 1, 0};
	static int[] dy2 = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			map[x][y].add(d);
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		shark = new Fish(x, y);
		
		while(s-- > 0) {
			doCopy();
			moveFish();
			moveShark();
			removeSmell();
			setCopy();
		}
		
		int ans = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				ans += map[i][j].size();
			}
		}
		System.out.println(ans);
	}
	
	private static void doCopy() {
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				for(int x : map[i][j]) {
					copied.add(new Fish(i, j, x));
				}
			}
		}
	}
	
	private static void moveFish() {
			
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				tmp[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				for(int x : map[i][j]) {
					
					boolean isMoved = false;
					for(int d = 8; d > 0; d--) {
						int dir = (d + x) % 8;
						int xx = i + dx[dir];
						int yy = j + dy[dir];
						
						if(xx < 0 || xx >= 4 || yy < 0 || yy >= 4 || smell[xx][yy] > 0 || (xx == shark.x && yy == shark.y)) continue;
						tmp[xx][yy].add(dir);
						isMoved = true;
						break;
					}
					
					if(!isMoved) tmp[i][j].add(x);
				}
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}
	
	private static void moveShark() {
		
		pos.clear();	
		dfs(0, shark.x, shark.y, "", 0);
		
		int[] result = pos.poll();
		int[] dir = new int[3];
		dir[0] = result[1] / 100;
		dir[1] = result[1] / 10 % 10;
		dir[2] = result[1] % 10;
		
		int x = shark.x;
		int y = shark.y;
		
		for(int d : dir) {
			x += dx2[d];
			y += dy2[d];
			if(map[x][y].size() > 0) {
				map[x][y].clear();
				smell[x][y] = 3;
			}
		}
		
		shark.x = x;
		shark.y = y;
	}
	
	private static void dfs(int depth, int x, int y, String route, int cnt) {
		
		if(depth == 3) {
			pos.add(new int[] {cnt, Integer.parseInt(route)});
			return;
		}
	
		for(int d = 0; d < 4; d++) {
			int xx = x + dx2[d];
			int yy = y + dy2[d];

			if(xx < 0 || xx >= 4 || yy < 0 || yy >= 4) continue;
			
			String nRoute = route.concat(String.valueOf(d));
			int nCnt = visited[xx][yy] > 0 ? cnt : cnt + map[xx][yy].size();
			visited[xx][yy]++;
			dfs(depth + 1, xx, yy, nRoute, nCnt);
			visited[xx][yy]--;
		}
	}
	
	private static void removeSmell() {
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(smell[i][j] > 0) smell[i][j]--;
			}
		}
	}
	
	private static void setCopy() {
		
		while(!copied.isEmpty()) {
			Fish fish = copied.poll();
			map[fish.x][fish.y].add(fish.d);
		}
	}	
	
}
