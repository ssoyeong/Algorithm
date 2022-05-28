package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 수열

public class BJ2559 {

	static int n;
	static int k;
	static int[] arr;
	static int sum;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) {
			
			if(i <= k) sum += arr[i];
			else {
				sum = sum - arr[i-k] + arr[i];
			}
			
			if(i >= k) max = Integer.max(max, sum);
		}
		
		System.out.println(max);
	}
}
