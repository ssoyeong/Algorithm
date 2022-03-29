package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 부분수열의 합

public class BJ1182_2 {
	
	static int n;
	static int s;
	static int[] arr;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(0, 0);
		
		if(s == 0) {
			System.out.println(cnt - 1);		// 공집합인 경우 제외
		}
		else {
			System.out.println(cnt);
		}

	}
	
	private static void backTracking(int depth, int total) {

		if(depth == n) {
			if(total == s) cnt++;
			return;
		}

		backTracking(depth + 1, total);					// 선택하지 않는 경우
		backTracking(depth + 1, total + arr[depth]);	// 선택하는 경우
	}
}
