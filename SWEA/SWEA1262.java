import java.io.*;
import java.util.*;

// 사람네트워크2

public class SWEA1262 {
	
	static final int MAX = 99999999;
	static int n;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			dist = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					int x = Integer.parseInt(st.nextToken());
					if(i == j) continue;
					if(x == 1) dist[i][j] = 1;
					else dist[i][j] = MAX;
				}
			}
			
			floydWarshall();
			
			int ans = MAX;
			for(int i = 0; i < n; i++) {
				int cnt = 0;
				for(int j = 0; j < n; j++) {
					cnt += dist[i][j];
				}
				ans = Integer.min(ans, cnt);
			}
			
			sb.append("#" + t + " " + ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void floydWarshall() {
		
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}

}
