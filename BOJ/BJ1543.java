package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 문서 검색

public class BJ1543 {
	
	static String doc;
	static String word;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		doc = br.readLine();
		word = br.readLine();
		
		while(true) {
			int idx = doc.indexOf(word);
			if(idx == -1) break;
			cnt++;
			doc = doc.substring(idx + word.length(), doc.length());
		}
		
		System.out.println(cnt);
	}

}
