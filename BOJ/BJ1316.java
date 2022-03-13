package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 그룹 단어 체커

public class BJ1316 {
	
	static int n;
	static String[] str;
	static int output;
	static char c;
	static char cPre;
	static String exist = "";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		str = new String[n];
		
		int i, j;
		for(i = 0; i < n; i++) {
			exist = "";
			str[i] = br.readLine();
			for(j = 0; j < str[i].length(); j++) {
				
				c = str[i].charAt(j);
				if(j == 0) {
					
					cPre = c;
					exist += c;
				}
				else {
					if(c != cPre) {
						if(exist.indexOf(c) >= 0) {
							break;
						}
						cPre = c;
					}
					exist += c;
				}
			}
			
			if(j == str[i].length()) output++;
		}
		
		System.out.println(output);
	}

}
