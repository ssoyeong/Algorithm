import java.io.*;
import java.util.*;

// 외판원 순회 2

public class BJ10971 {

	static int n;
	static int ans = Integer.MAX_VALUE;
	static int[] order;
	static int[][] adj;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		order = new int[n];
		adj = new int[n][n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
			order[i] = i;
		}
		
		solution();
		System.out.println(ans);
	}
	
	private static void solution() {
		
		do {
			int total = 0;
			boolean flag = false;
			
			for(int i = 0; i < n; i++) {
				int cost = adj[order[i]][order[(i + 1) % n]];
				if(cost == 0) {
					flag = true;
					break;
				}
				total += cost;
			}
			if(!flag) ans = Integer.min(ans, total);
			
		} while(nextPermutation());
	}
	
	private static boolean nextPermutation() {
		
		int i = n - 1;
		while(i > 1 && order[i - 1] >= order[i]) i--;
		if(i == 1) return false;
		
		int j = n - 1;
		while(order[i - 1] >= order[j]) j--;
		swap(i - 1, j);
		
		int k = n - 1;
		while(i < k) swap(i++, k--);
		return true;
	}
	
	private static void swap(int a, int b) {
		int tmp = order[a];
		order[a] = order[b];
		order[b] = tmp;
	}
}
