package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 뒤집기

public class BJ1439 {
	
	static String str;
	static int zero;		// 연속한 0의 묶음
	static int one;			// 연속한 1의 묶음
	static char pre;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		
		pre = str.charAt(0);
		if(pre == '0') zero++;
		else one++;
		
		for(int i = 1; i < str.length(); i++) {
			if(pre != str.charAt(i) && pre == '0') one++;
			else if(pre != str.charAt(i) && pre == '1') zero++;
			pre = str.charAt(i);
		}
		
		System.out.println(Math.min(one, zero));
	}
}
