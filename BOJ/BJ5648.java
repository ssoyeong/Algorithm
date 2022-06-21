package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 역원소 정렬

public class BJ5648 {
	
	static int n;
	static int cnt;
	static long[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		arr = new long[n];
		
		while(st.hasMoreTokens()) {
			long output = convert(st.nextToken());
			arr[cnt] = output;
			cnt++;
		}
		
		while(cnt < n) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				long output = convert(st.nextToken());
				arr[cnt] = output;
				cnt++;
			}
		}

		Arrays.sort(arr);
		for(long num : arr) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	private static long convert(String str) {
		
		StringBuilder sb2 = new StringBuilder(str);
		String reverse = sb2.reverse().toString();
		long num = Long.parseLong(reverse);
		return num;
	}

}
