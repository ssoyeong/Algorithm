package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// String

public class SWEA1213 {
	
	static String target;
	static String line;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =  new StringBuilder();
		for(int tc = 1; tc <= 10; tc++) {
			
			cnt = 0;
			br.readLine();
			target = br.readLine();
			line = br.readLine();
			
			for(int i = 0; i <= line.length() - target.length(); i++) {
				if(line.substring(i, i + target.length()).equals(target)) {
					cnt++;
				}
			}
			sb.append(String.format("#%d %d\n", tc, cnt));
			
		}
		
		System.out.println(sb.toString());
	}

}
