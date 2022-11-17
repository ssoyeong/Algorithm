package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 상호의 배틀필드  

class SWEA1873 {

	static int t;
	static int h, w, n;
	static char[][] arr;
	static String cmd;
	static int direct; // 상우하좌 0123
	static int x, y;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {

			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			arr = new char[h][w];

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					arr[i][j] = line.charAt(j);

					if (arr[i][j] == '^') {
						x = i;
						y = j;
						direct = 0;
					} else if (arr[i][j] == '>') {
						x = i;
						y = j;
						direct = 1;
					} else if (arr[i][j] == 'v') {
						x = i;
						y = j;
						direct = 2;
					} else if (arr[i][j] == '<') {
						x = i;
						y = j;
						direct = 3;
					}
				}
			}
			
			n = Integer.parseInt(br.readLine());
			cmd = br.readLine();
			
			solution();
			
			sb.append("#" + tc + " ");
			for(int r = 0; r < h; r++) {
				for(int c = 0; c < w; c++) {
					sb.append(arr[r][c]);
				}
				sb.append("\n");
			}
			
		}
		
		System.out.println(sb.toString());

	}

	private static void solution() {
		
		for(int i = 0; i < n; i++) {
			if(cmd.charAt(i) == 'U') {
				move(0);
			}
			else if(cmd.charAt(i) == 'R') {
				move(1);
			}
			else if(cmd.charAt(i) == 'D') {
				move(2);
			}
			else if(cmd.charAt(i) == 'L') {
				move(3);
			}
			else {
				shoot();
			}
			
		}
	}
	
	private static void move(int direct_cmd) {
		
		direct = direct_cmd;
		int xx = x + dx[direct];
		int yy = y + dy[direct];
		
		switch(direct) {
		case 0: arr[x][y] = '^';
				break;
		case 1: arr[x][y] = '>';
				break;
		case 2: arr[x][y] = 'v';
				break;
		default: arr[x][y] = '<';
		}
		
		if(xx < 0 || xx >= h || yy < 0 || yy >= w) return;
		
		
		if(arr[xx][yy] == '.') {
			arr[xx][yy] = arr[x][y];
			arr[x][y] = '.';
			x = xx;
			y = yy;
		}
	}
	
	private static void shoot() {
		
		int bomb_x = x;
		int bomb_y = y;
		
		if(direct == 1 || direct == 3) {	// 우 or 좌를 바라보고 있는 경
			while(true) {
				bomb_y = bomb_y + dy[direct];
				if(bomb_y < 0 || bomb_y >= w) return;
				if(arr[bomb_x][bomb_y] == '*') {
					arr[bomb_x][bomb_y] = '.';
					return;
				}
				if(arr[bomb_x][bomb_y] == '#') return;
			}
		}
		else {		// 상 or 하를 바라보고 있는 경우 
			while(true) {
				bomb_x = bomb_x + dx[direct];
				if(bomb_x < 0 || bomb_x >= h) return;
				if(arr[bomb_x][bomb_y] == '*') {
					arr[bomb_x][bomb_y] = '.';
					return;
				}
				if(arr[bomb_x][bomb_y] == '#') return;
			}
		}
	}
	
}