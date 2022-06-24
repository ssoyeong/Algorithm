package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 단어 뒤집기 2


public class BJ17413 {
	
	static boolean tagFlag;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "<> ", true);
		StringBuilder sb = new StringBuilder();
		
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			
			if(token.equals("<")) {
				tagFlag = true;
				sb.append("<");
				continue;
			}
			
			if(token.equals(">")) {
				sb.append(">");
				tagFlag = false;
				continue;
			}
			
			if(tagFlag) {
				sb.append(token);
			}
			else {
				StringBuilder sb_reverse = new StringBuilder();
				sb_reverse.append(token);
				sb_reverse.reverse();
				sb.append(sb_reverse);
			}
			
		}
		
		System.out.println(sb.toString());
		
	}

}
