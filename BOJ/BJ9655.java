package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 돌 게임

public class BJ9655 {
	
	static int n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		if(n % 2 != 0) System.out.println("SK");
		else System.out.println("CY");
	}
}
