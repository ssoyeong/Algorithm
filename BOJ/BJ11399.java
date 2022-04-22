package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// ATM

public class BJ11399 {
	
	static int n;
	static Integer[] arr;
	static long total;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new Integer[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		arr[n] = Integer.MAX_VALUE;
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		for(int i = 1; i <= n; i++) {
			total += i * arr[i];
		}
		
		System.out.println(total);
	}

}
