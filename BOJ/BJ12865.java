package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 평범한 배낭

public class BJ12865 {
	
	static int n;
	static int k;
	static int[] weight;
	static int[] value;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		weight = new int[k + 1];
		value = new int[n];
		dp = new int[k + 1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			weight[i] = w;
			value[i] = v;
			
			for(int j = k; j >= w; j--) {
				dp[j] = Math.max(v + dp[j - w], dp[j]);
			}
		}
		
		System.out.println(dp[k]);
	}

}
