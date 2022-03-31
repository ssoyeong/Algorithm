package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 4

public class BJ14002 {
	
	static int n;
	static int[] arr;
	static int[] dp;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];
		Arrays.fill(dp, 1);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		doDP();
		printResult();
	}
	
	private static void doDP() {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
	}
	
	private static void printResult(){
		
		int[] copy = dp.clone();
		Arrays.sort(copy);
		int max = copy[copy.length - 1];
		System.out.println(max);
		answer = new int[max+1];
		
		for(int i = dp.length - 1; i >= 0; i--) {
			if(dp[i] == max) {
				answer[max] = arr[i];
				max--;
				
				if(max == 0) {
					for(int j = 1; j < answer.length; j++) {
						System.out.print(answer[j] + " ");
					}
					System.out.println();
					return;
				}
			}
		}

	}
}
