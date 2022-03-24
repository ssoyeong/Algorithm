package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 부분수열의 합

public class BJ1182 {
	
	static int n;
	static int s;
	static int[] arr;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 1; i <= Math.pow(2, n) - 1; i++) {
			
			int total = sumIntegers(i);
			if(total == s) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	private static int sumIntegers(int bit) {
		int total = 0;
		for(int i = 0; i < n ; i++) {
			int mask = bit & (1 << i);		// 부분수열 index를 비트로 표현하여 1인 경우만 값을 더함
			if(mask > 0) {
				total += arr[i];
			}
		}
		return total;
	}

}
