package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

// 대칭 차집합

public class BJ1269 {
	
	static int a, b;
	static HashSet<Integer> setA = new HashSet<>();
	static HashSet<Integer> setB = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < a; i++) {
			setA.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < b; i++) {
			setB.add(Integer.parseInt(st.nextToken()));
		}
		
		setA.retainAll(setB);
		int answer = a + b - (setA.size() * 2);
		System.out.println(answer);
	}

}
