package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;

// 30

public class BJ10610 {
	
	static String[] n;
	static int total;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = br.readLine().split("");
		
		if(!Arrays.asList(n).contains("0")) {		// 10의 배수가 되기 위해 0이 있는지 판단
			System.out.println("-1");
			System.exit(0);
		}
		
		for(int i = 0; i < n.length; i++) {
			total += Integer.parseInt(n[i]);		
		}
		
		if(total % 3 != 0) {
			System.out.println("-1");				// 3의 배수가 되기 위해 각 자리 숫자의 합이 3의 배수인지 판단
			System.exit(0);
		}
		
		Arrays.sort(n, Collections.reverseOrder());	// 가장 큰 수를 만들어야 하므로 역순 정렬
		for(String x : n) {
			sb.append(x);
		}
		
		System.out.println(sb.toString());
	}

}
