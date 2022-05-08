package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 색종이 만들기

public class BJ2630 {
	
	static int n;
	static int white;
	static int blue;
	static boolean[][] paper;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		paper = new boolean[n+1][n+1];
		
		StringTokenizer st;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				if(st.nextToken().equals("1")) {
					paper[i][j] = true;
				}
			}
		}
		
		recursion(1, 1, n);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void recursion(int row, int col, int size) {
		
		int result = isDivided(row, col, size);
		if(result == 0) {
			white++;
			if(size == 1) return;
		}
		else if(result == 1) {
			blue++;
			if(size == 1) return;
		}
		else {
			
			recursion(row, col, size/2);
			recursion(row, col + size/2, size/2);
			recursion(row + size/2, col, size/2);
			recursion(row + size/2, col + size/2, size/2);
		}
	}
	
	private static int isDivided(int row, int col, int size) {		// -1이라면 완전히 나뉘어지지 않음 / 0이라면 white로 나뉨 / 1이라면 blue로 나뉨
		
		boolean color = paper[row][col];
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) {
				if(paper[i][j] != color) return -1;
			}
		}
		
		if(color) return 1;
		return 0;
	}
}
