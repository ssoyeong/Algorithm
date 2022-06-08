package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.io.IOException;

// 서로 다른 부분 문자열의 개수

public class BJ11478 {
	
	static String input;
	static HashSet<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		
		for(int i = 0; i < input.length(); i++) {
			for(int j = i; j < input.length(); j++) {
				
				String sub = input.substring(i, j+1);
				
				if(!set.contains(sub)) set.add(sub);
			}
		}
		
		System.out.println(set.size());
	}

}
