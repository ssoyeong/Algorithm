package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

// 소수의 연속합

public class BJ1644 {
	
	static int n;
	static int start = -1;
	static int end = -1;
	static int cnt;
	static int total;
	static boolean[] isNotPrime;
	static ArrayList<Integer> primes = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		if(n == 1) {
			System.out.println(0);
			System.exit(0);
		}
		
		isNotPrime = new boolean[n+1];
	
		checkPrime();
		
		while(true) {
			
			if(total >= n) {
				if(total == n) cnt++;
				start++;
				total -= primes.get(start); 
			}
			else {
				end++;
				total += primes.get(end);
			}
			
			if(end == primes.size() - 1) {			// end가 마지막 idx에 도달하면
				if(primes.get(end) == n) cnt++;		// 마지막 수가 소수라면 cnt++하고
				break;								// 종료
			}
		}
		
		System.out.println(cnt);
	}
	
	private static void checkPrime() {
		
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		
		for(int i = 2; i * i <= n; i++) {		// 에라토스테네스의 체 소수 판별
			if(!isNotPrime[i]) {
				for(int j = i * i; j <= n; j += i) {
					isNotPrime[j] = true;
				}
			}
		}
		
		for(int i = 0; i <= n; i++) {
			if(!isNotPrime[i]) primes.add(i);	// 소수만 primes에 담음
		}
	}


}
