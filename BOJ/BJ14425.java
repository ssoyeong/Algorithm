package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

// 문자열 집합

public class BJ14425 {
	
	static int numSet;
	static int numCheck;
	static HashSet<String> arr = new HashSet<>();
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numSet = Integer.parseInt(st.nextToken());
		numCheck = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < numSet; i++) {
			arr.add(br.readLine());
		}
		
		for(int i = 0; i < numCheck; i++) {
			if(arr.contains(br.readLine())) cnt++;
		}
		
		System.out.println(cnt);
	}
}
