package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 수들의 합

public class BJ1789 {
	
	static long n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Long.parseLong(br.readLine());
		
		double db = Math.sqrt(n * 2 + 0.25);
		db = Math.ceil(db - 0.5);
		int ans = (int)db;
		
		if(((long) (Math.pow(ans, 2) + ans) / 2) > n) ans--;
		System.out.println(ans);
	}

}
