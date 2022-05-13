package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 배열 돌리기 1

public class BJ16926 {
	
	static int n;
	static int m;
	static int r;
	static int[][] arr;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cycle = Math.min(n, m) / 2;		// 1회전 당 돌려야 하는 그룹 개수
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < cycle; j++) {
				
				int x = j;
				int y = j;
				int temp = arr[x][y];
			
				int idx = 0;
				while(idx < 4) {
					
					int xx = x + dx[idx];
					int yy = y + dy[idx];
					if(xx < j || xx >= n-j || yy < j || yy >= m-j) {
						idx++;
						continue;
					}
					
					arr[x][y] = arr[xx][yy];
					x = xx;
					y = yy;
				}
				
				arr[j+1][j] = temp;
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
