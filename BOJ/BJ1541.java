package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ1541 {
	
	static String[] arr = new String[50];
	static int total;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, "-");		// - 기준 분할
		
		boolean isFirst = true;
		while(st.hasMoreTokens()) {

			String token = st.nextToken();
			int subTotal = 0;
			
			StringTokenizer st2 = new StringTokenizer(token, "+");	// + 기준 분할
			while(st2.hasMoreTokens()) {
				subTotal += Integer.parseInt(st2.nextToken());
			}
			
			if(isFirst) {				// 처음 나오는 + 묶음은 더해준다.
				total += subTotal;
				isFirst = false;
			}
			else total -= subTotal;		// 그 이후 + 묶음은 - 기준 분할했으므로 뻬준다.
		}
		
		
		System.out.println(total);
	}

}
