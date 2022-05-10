package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 별 짝기 - 10

public class BJ2447 {
	
	static int n;
	static String[][] board;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new String[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j] = " ";
			}
		}	
		
		print(0, 0, n);
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void print(int r, int c, int size) {
		
		if(size == 1) {
			board[r][c] = "*";
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 1 && j == 1) {
					continue;
				}
				print(r + i * (size/3), c + j * (size/3), size/3);
			}
		}
	}
	
}
