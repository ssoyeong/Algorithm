package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 한수

public class BJ1065 {
	
	static int x;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		x = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= x; i++) {
			solution(i);
		}
		
		System.out.println(cnt);
	}
	
	private static void solution(int x) {
		
		if(x < 100) cnt++;
		else {
			int a = x / 100;
			int b = (x / 10) % 10;
			int c = x % 10;
			
			if(a <= b && b <= c && (a + c) == 2*b) cnt++;
			else if(a >= b && b >= c && (a + c) == 2*b) cnt++;
		}
	}

}
