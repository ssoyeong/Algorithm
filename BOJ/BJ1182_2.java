package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
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
		
		Arrays.sort(arr);
		
//		for(int i : arr) System.out.print(i + " ");
//		System.out.println();
		
		backTracking(0, 0, 0);
		
		
		System.out.println(cnt);
	}
	
	private static void backTracking(int depth, int idx, int total) {
		
		if(total == s && depth != 0) {
			cnt++;
//			System.out.println("만족할 때 depth: " + depth);
			return;
		}

		for(int i = idx; i < arr.length; i++) {
			if(depth == 0) total = 0;
			total += arr[i];
//			System.out.println("depth: " + depth + ", i: " + i + ", total: " + total);
			backTracking(depth+1, i+1, total);
			total -= arr[i];
		}
		
		
	}
	


}
