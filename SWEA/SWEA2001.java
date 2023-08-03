import java.io.*;
import java.util.*;

// 파리 퇴치

public class SWEA2001 {
	
	static int tc;
	static int n, m;
	static int[][] board;
	static int[][] matrix;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t = 1; t <= tc; t++) {

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			board = new int[n + 1][n + 1];
			matrix = new int[n + 1][n + 1];	

			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = solution();
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}

	private static int solution() {

		makeMatrix();

		// m * m 크기의 최댓값 구하기
		int max = 0;
		for(int i = m; i <= n; i++) {
			for(int j = m; j <= n; j++) {
				// 왼쪽 하단을 (i, j)를 기준으로 해서 m 크기의 파리채
				max = Integer.max(max, matrix[i][j] - matrix[i - m][j] - matrix[i][j - m] + matrix[i - m][j - m]);
			}
		}

		return max;
	}

	private static void makeMatrix() {

		for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == 1) {
                    matrix[i][j] = matrix[i][j-1] + board[i][j];
                }
                else if(j == 1) {
                    matrix[i][j] = matrix[i-1][j] + board[i][j];
                }
                else {
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j-1] + board[i][j] - matrix[i-1][j-1];
                }
            }
        }
	}
}
