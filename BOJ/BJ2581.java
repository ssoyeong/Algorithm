package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

// 소수

public class BJ2581 {
	
	static int m;
	static int n;
	static boolean[] isPrime;
	static ArrayList<Integer> primes = new ArrayList<>();
	static int min;
	static int total;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		
		isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		for(int i = 2; i * i <= n; i++) {
			
			if(isPrime[i]) {
				
				for(int j = i * i; j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		boolean flag = false;		// min을 담을 때 사용
		for(int i = m; i <= n; i++) {
			if(isPrime[i]) {
				if(!flag) {
					min = i;
					flag = true;
				}
				total += i;
			}
		}
		
		if(!flag) System.out.println("-1");
		else {
			System.out.println(total);
			System.out.println(min);
		}
	}

}
