package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// N-Queen

public class SWEA2806 {

	static int t;
	static int n;
	static int[] arr;
	static int cnt;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= t; tc++) {
			
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			cnt = 0;
			backTracking(0);
			
			
			sb.append(String.format("#%d %d\n", tc, cnt));
		}
		
		System.out.println(sb.toString());
	}
	
	private static void backTracking(int depth) {
		
		if(depth == n) {
			cnt++;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			
			arr[depth] = i;
			if(isPromising(depth)) {
				
				backTracking(depth + 1);
			}
		}
	}
	
	private static boolean isPromising(int col) {
		
		for(int i = 0; i < col; i++) {
			
			if(arr[col] == arr[i] || Math.abs(col - i) == Math.abs(arr[col] - arr[i])) return false;
		}
		
		return true;
	}


}
