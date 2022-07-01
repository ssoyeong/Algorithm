package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 3의 배수

public class BJ1769 {

	static String X;
	static int num;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		X = br.readLine();
		
		if(X.length() == 1) {
			print(Integer.parseInt(X));
			return;
		}
		else {
			for(int i = 0; i < X.length(); i++) {
				num += Character.getNumericValue(X.charAt(i));
			}
			cnt++;
		}
		
		while(num/10 > 0) {
			
			num = calc(num);
			cnt++;
		}
		
		print(num);
	}
	
	private static void print(int num) {
		
		System.out.println(cnt);
		if(num % 3 == 0) System.out.println("YES");
		else System.out.println("NO");
	}
	
	private static int calc(int n) {
		
		int result = 0;
		while(n/ 10 > 0) {
			result += n % 10;
			n = (n - n%10) / 10;
			
		}
		
		result += n;
		return result;
	}
}
