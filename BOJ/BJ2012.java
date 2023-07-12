import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

// 등수 매기기

public class BJ2012 {
	
	static int n;
	static long[] rank;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new java.io.BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		rank = new long[n + 1];
		
		for(int i = 1; i <= n; i++) {
			rank[i] = Long.parseLong(br.readLine());
		}
		
		long ans = solution();
		System.out.println(ans);
	}
	
	private static long solution() {
		
		Arrays.sort(rank);
		
		long total = 0;
		for(int i = 1; i <= n; i++) {
			total += Math.abs(i - rank[i]);
		}
		
		return total;
	}

}
