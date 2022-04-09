package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

// 접두사 찾기

public class BJ14426 {
	
	static int numSet;
	static int numCheck;
	static HashMap<String, Integer> arr = new HashMap<>();
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numSet = Integer.parseInt(st.nextToken());
		numCheck = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < numSet; i++) {
			arr.put(br.readLine(), i);
		}
		
		for(int i = 0; i < numCheck; i++) {
			String check = br.readLine();
			for(String key : arr.keySet()) {
				boolean startsWith = key.startsWith(check);
				if(startsWith) {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}
}
