import java.io.*;
import java.util.*;

// 파일 합치기

public class BJ11066 {
	
	static int t;
	static int k;
	static int[] size;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc < t; tc++) {
			k = Integer.parseInt(br.readLine());
			size = new int[k + 1];
			dp = new int[k + 1][k + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= k; i++) {
				size[i] = Integer.parseInt(st.nextToken());
				for(int j = 1; j <= k; j++) {
					dp[i][j] = Integer.MAX_VALUE;
					if(i == j) dp[i][j] = 0;
				}
			}
			
			solution();
			sb.append(dp[1][k] + "\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void solution() {
		
		for(int diff = 1; diff < k; diff++) {
			for(int s = 1; s <= k - diff; s++) {
				calculate(s, s + diff);
			}
		}
	}
	
	private static void calculate(int start, int end) {
		
		for(int i = 0; i < end - start; i++) {
			dp[start][end] = Integer.min(dp[start][end], dp[start][start + i] + dp[start + i + 1][end]);
		}
		
		for(int i = start; i <= end; i++) {
			dp[start][end] += size[i];
		}
	}

}
