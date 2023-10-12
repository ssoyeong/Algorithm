import java.io.*;
import java.util.*;

public class CT메이즈러너 {

	static int n, m, k;
	static int total, cnt;
	static int ex, ey;
	static int[][] map;
	static int[][] man;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		cnt = m;
		map = new int[n][n];
		man = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			man[r][c]++;
		}
		
		st = new StringTokenizer(br.readLine());
		ex = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;
		
		solution();
		System.out.println(total);
		System.out.println((ex + 1) + " " + (ey + 1));
	}
	
	private static void solution() {
		
		while(k-- > 0) {
			moveMan();
			if(cnt == 0) break;
			rotate();
		}
	}
	
	private static void moveMan() {
		
		int[][] tmp = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(man[i][j] > 0) {
					
					int dist = getDist(i, j, ex, ey);
					
					boolean flag = false;
					for(int d = 0; d < 4; d++) {
						int xx = i + dx[d];
						int yy = j + dy[d];
						if(xx < 0 || xx >= n || yy < 0 || yy >= n || map[xx][yy] != 0) continue;
						
						int dd = getDist(xx, yy, ex, ey);
						if(dd == 0) {
							flag = true;
							total += man[i][j];
							cnt -= man[i][j];
							break;
						}
						if(dd < dist) {
							flag = true;
							total += man[i][j];
							tmp[xx][yy] += man[i][j];
							break;
						}
					}
					
					if(!flag) tmp[i][j] += man[i][j];
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				man[i][j] = tmp[i][j];
			}
		}
	}
		
	private static void rotate() {
		
		for(int len = 2; len <= n; len++) {
			for(int i = 0; i < len; i++) {
				
				int r = ex - (len - 1) + i;
				if(r + len - 1 >= n) break;
				
				for(int j = 0; j < len; j++) {
					
					int c = ey - (len - 1) + j;
					if(c + len - 1 >= n) break; 
					
					if(isValid(r, c, len)) {
						doRotate(r, c, len);
						return;
					}
				}
			}
		}
	}
	
	private static boolean isValid(int r, int c, int len) {
		
		if(r < 0 || r >= n || c < 0 || c >= n) return false;
		for(int i = r; i < r + len; i++) {
			for(int j = c; j < c + len; j++) {
				if(man[i][j] > 0) return true;
			}
		}
		return false;
	}
	
	private static void doRotate(int r, int c, int len) {
		
		int nex = ex;
		int ney = ey;
		int[][] tmpMap = new int[n][n];
		int[][] tmpMan = new int[n][n];
		
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				
				int x = r + i;
				int y = c + j;
				int nx = r + j;
				int ny = c + (len - i - 1);
				tmpMap[nx][ny] = map[x][y];
				if(tmpMap[nx][ny] > 0) tmpMap[nx][ny]--;
				tmpMan[nx][ny] = man[x][y];
				if(x == ex && y == ey) {
					nex = nx;
					ney = ny;
				}
			}
		}
		
		for(int i = r; i < r + len; i++) {
			for(int j = c; j < c + len; j++) {
				map[i][j] = tmpMap[i][j];
				man[i][j] = tmpMan[i][j];
			}
		}
		
		ex = nex;
		ey = ney;
	}
	
	private static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
