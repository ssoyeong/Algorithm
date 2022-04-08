package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 문자열 폭발

public class BJ9935 {
	
	static String str;
	static String exp;
	static int strLen;
	static int expLen;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		exp = br.readLine();
		strLen = str.length();
		expLen = exp.length();
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < strLen; i++) {
			
			sb.append(str.charAt(i));
			if(sb.length() >= exp.length()) {
				if(sb.substring(sb.length() - expLen, sb.length()).equals(exp)) {		// sb의 뒤에서부터 expLen개 문자 가져와서, exp와 비교
					sb.delete(sb.length() - expLen, sb.length());
				}
			}
			
		}
		
		if(sb.length() == 0) {
			sb.append("FRULA");
		}
		System.out.println(sb.toString());
	}
}
