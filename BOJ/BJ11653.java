package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 소인수분해

public class BJ11653 {
	
	static int n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		if(n == 1) return;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i <= n; i++) {
		
			while(n % i == 0) {
				sb.append(i).append("\n");
				n = n / i;
			}
		}
		
		System.out.println(sb.toString());
	}

}
