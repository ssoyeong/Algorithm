package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 햄버거 다이어트 

public class SWEA5215 {
	
	static int t;
	static int n, limit;
	static int[] score;
	static int[] cal;
	static int result;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= t; tc++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			score = new int[n];
			cal = new int[n];
			result = 0;
			
			for(int i = 0; i < n; i++) {
				
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			
			solution();
			sb.append(String.format("#%d %d\n", tc, result));
			
		}
		
		System.out.println(sb.toString());
		
	}
	
	private static void solution() {
		
		for(int i = 1; i <= Math.pow(2, n); i++) {
			
			int sum_score = 0;
			int sum_cal = 0;
			for(int digit = 0; digit < n; digit++) {

				int x = i & (1 << digit);
				if(x != 0) {
					sum_cal += cal[digit];
					sum_score += score[digit];
				}
				
			}
				
			if(sum_cal <= limit) {
				result = Math.max(result, sum_score);
			}
		}
	}

}

