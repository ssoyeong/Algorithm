package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 괄호

public class BJ9012 {
	
	static int n;
	static String[] arr;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			
			cnt = 0;
			arr = br.readLine().split("");
			
			for(int j = 0; j < arr.length; j++) {
	
				if(arr[j].equals("(")) cnt++;		// 여는 괄호라면 cnt 증가
				else {								// 닫는 괄호라면 cnt 감소
					cnt--;
					if(cnt < 0) break;
				}
			}
			
			if(cnt == 0) sb.append("YES\n");		// VPS라면 열고 닫는 횟수가 같기 때문에 최종적으로 cnt는 0이 되어야 한다.
			else sb.append("NO\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
