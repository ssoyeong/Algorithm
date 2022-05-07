package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 단어 뒤집기

public class BJ9093 {
	
	static int n;
	
	static class Solution {
		
		static private String solution(String input) {
			
			StringBuilder sb = new StringBuilder();
			StringBuilder reverseSb;
			StringTokenizer st = new StringTokenizer(input);
			while(st.hasMoreTokens()) {
				reverseSb = new StringBuilder();
				reverseSb.append(st.nextToken()).reverse();
				sb.append(reverseSb).append(" ");
			}
			
			return sb.toString();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			sb.append(Solution.solution(br.readLine())).append("\n");

		}
		
		System.out.println(sb.toString());
	}
}
