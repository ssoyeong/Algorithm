package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 숨바꼭질 2

public class BJ12851 {
	
	static int n;
	static int k;
	static int cnt;
	static int[] dp = new int[100001];
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if(n == k) {
			System.out.println("0\n1");
			System.exit(0);
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		queue.add(new int[] {n, 0});
		
		while(!queue.isEmpty()) {
			
			int[] poll = queue.poll();
			int pos = poll[0];
			int cost = poll[1];
			
			if(pos-1 >= 0 && pos-1 <= 100000 && dp[pos-1] >= cost+1) {
				
				dp[pos-1] = cost + 1;
				queue.add(new int[] {pos-1, cost+1});
				

				if(pos-1 == k) {
					if(dp[pos-1] > cost+1) {
						cnt = 1;
					}
					else cnt++;
				}
				
			}
			if(pos+1 >= 0 && pos+1 <= 100000 && dp[pos+1] >= cost+1) {
				dp[pos+1] = cost + 1;
				queue.add(new int[] {pos+1, cost+1});
				
				if(pos+1 == k) {
					if(dp[pos+1] > cost+1) {
						cnt = 1;
					}
					else cnt++;
				}
			
				
			}
			if(pos*2 >= 0 && pos*2 <= 100000 && dp[pos*2] >= cost+1) {
				dp[pos*2] = cost + 1;
				queue.add(new int[] {pos*2, cost+1});
				
				if(pos*2 == k) {
					if(dp[pos*2] > cost+1) {
						cnt = 1;
					}
					else cnt++;
				}
			}
		}
		
		System.out.println(dp[k]);
		System.out.println(cnt);
	}
}
