package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 최대공약수와 최소공배수

public class BJ2609 {
	
	static int a, b;
	static int gcd;
	static int lcm;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		gcd = calcGCD(a, b);
		lcm = a*b / gcd;
		
		System.out.println(gcd);
		System.out.println(lcm);
		
	}
	
	private static int calcGCD(int a, int b) {
		
		if(a%b == 0) return b;
		else return calcGCD(b, a%b);
	}

}
