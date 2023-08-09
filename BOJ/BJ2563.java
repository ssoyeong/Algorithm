import java.io.*;
import java.util.*;

// 색종이

public class BJ2563 {

	static final int SIZE = 100;		// 보드의 사이즈
	static final int LENGTH = 10;		// 색종이의 한 변의 길이
	static int n;
	static int cnt;
	static boolean[][] visited = new boolean[SIZE + 1][SIZE + 1];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			stickPaper(r, c);
		}
		
		System.out.println(cnt);
	}
	
	private static void stickPaper(int r, int c) {
		
		for(int i = r; i < r + LENGTH; i++) {
			for(int j = c; j < c + LENGTH; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					cnt++;
				}
			}
		}
	}
	
}
