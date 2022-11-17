package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 회문2


public class SWEA1216 {
	
	static char[][] arr;
	static boolean[][] visited;
	static int max;
	final static int SIZE = 100;
	

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			
			br.readLine();
			arr = new char[SIZE][SIZE];
			visited = new boolean[SIZE][SIZE];
			max = 0;
			
			for(int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for(int j = 0; j < SIZE; j++) {
					arr[i][j] = line.charAt(j);
				}
			}
			
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					dfs(i, j, 1);
				}
			}
			
			sb.append(String.format("#%d %d\n", tc, max));
		}
		
		System.out.println(sb.toString());
		

	}
	
	private static void dfs(int x, int y, int cnt) {
		
		if(x + cnt > SIZE || y + cnt > SIZE) return;
		
		if(isPalindrome(x, y, cnt)) max = Integer.max(max, cnt);
		dfs(x, y, cnt+1);
		
	}
	
	private static boolean isPalindrome(int x, int y, int length) {
		
		StringBuilder sb_row = new StringBuilder();
		StringBuilder sb_col = new StringBuilder();
		for(int i = 0; i < length; i++) {
			sb_row.append(arr[x][y + i]);
			sb_col.append(arr[x + i][y]);
			
		}
		
		String word_row = sb_row.toString();
		String word_col = sb_col.toString();
		
		if(word_row.equals(sb_row.reverse().toString())) return true;
		if(word_col.equals(sb_col.reverse().toString())) return true;
		return false;
	}

}
