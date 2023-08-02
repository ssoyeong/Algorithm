package a0802;

import java.io.*;
import java.util.*;

// 달팽이 숫자

public class SWEA1954 {
	
	static int tc;
	static int n;
	static int[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};


	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {

			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			
			solution();
			sb.append("#" + t + "\n");
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sb.append(board[i][j] + " ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	private static void solution() {

		int x = 0;
		int y = 0;
		int num = 1;
		int d = 0;

		while(num <= n * n) {
			board[x][y] = num++;

			int xx = x + dx[d % 4];
			int yy = y + dy[d % 4];

			if(xx < 0 || xx >= n || yy < 0 || yy >= n || board[xx][yy] != 0) {
				d++;
				x = x + dx[d % 4];
				y = y + dy[d % 4];
			}
			else {
				x = xx;
				y = yy;
			}
		}
	}
	
}
