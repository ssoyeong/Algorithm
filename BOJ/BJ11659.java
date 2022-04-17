package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 구간 합 구하기 4

public class BJ11659 {

	static int n;
	static int m;
	static int[] total;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		total = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			
			if(i == 1) total[i] = Integer.parseInt(st.nextToken());
			else total[i] = total[i-1] + Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < m; i++) {
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			long sum = total[end] - total[start-1];
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
