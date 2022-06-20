package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 신기한 소수

public class BJ2023 {
	
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		backTracking(0, 0);
		System.out.println(sb.toString());
	}
	
	public static void backTracking(int x, int depth) {
		
		if(depth == N) {
			sb.append(x).append("\n");
			return;
		}
		
		for(int i = 0; i <= 9; i++) {
			if(depth == 0 && i == 0) continue;

			int check = x * 10 + i;
			if(isPrime(check)) {
				backTracking(check, depth+1);
			}
		}
	}
	
	public static boolean isPrime(int n) {
		
		if(n == 1) return false;
		for(int i = 2; i*i <= n; i++) {
			if(n%i == 0) return false;
		}
		return true;
	}
} 
