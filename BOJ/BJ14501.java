package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 퇴사

class Sked {
	
	int period;
	int cost;
	
	public Sked(int period, int cost) {
		this.period = period;
		this.cost = cost;
	}
}

public class BJ14501 {
	
	static int n;
	static Sked[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new Sked[n+1];
		dp = new int[n+2];
		
		StringTokenizer st;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Sked sk = new Sked(p, c);
			arr[i] = sk;
		}
		
		// Dynamic Programming
		for(int i = n; i > 0; i--) {

			if((i + arr[i].period) > n + 1) {
				dp[i] = dp[i+1];
				continue;
			}
			
			if(i == n) dp[i] = arr[i].cost;
			else dp[i] = Math.max(dp[i+1], dp[i+arr[i].period] + arr[i].cost);
		}
		
		System.out.println(dp[1]);
	}
}
