package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.IOException;

// 보물

public class BJ1026 {
	
	static int n;
	static Integer[] A;
	static Integer[] B;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		A = new Integer[n];
		B = new Integer[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			A[i] = x;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			B[i] = x;
		}
		
		Arrays.sort(A);
		Arrays.sort(B, Collections.reverseOrder());
		
		int answer = 0;
		for(int i = 0; i < n; i++) {
			answer += A[i] * B[i];
		}
		
		System.out.println(answer);
	}

}
