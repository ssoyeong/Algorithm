package baekjoon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

//두 소수의 합으로 짝수 표현

public class BJ9020 {
	
	static int t;
	static int n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			
			n = Integer.parseInt(br.readLine());
			
			for(int j = n/2; j >= 2; j--) {
				
				int x = n - j;
				if(n != 4) {
					if(j%2 == 0) continue;
				}
				
				if(isPrime(j) && isPrime(x)) {
					bw.write(j + " " + x + "\n");
					break;
				}
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private static boolean isPrime(int n) {
		
		if(n == 1) return false;
		for(int i = 2; i < n; i++) {
			if(n % i == 0) return false;
		}
		
		return true;
	}

}
