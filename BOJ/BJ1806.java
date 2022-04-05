package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 부분 합

public class BJ1806 {
	
	static int n;
	static int s;
	static int[] arr;
	static int length = Integer.MAX_VALUE;
	static int start;
	static int end;
	static int total;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			
			if(total >= s){
				length = Math.min(length, end - start);
				total -= arr[start];
				start++;
			}
			else if(end == n) break;
			else {
				total += arr[end];
				end++;
			}	
		}
		
		if(length == Integer.MAX_VALUE) length = 0;
		System.out.println(length);
	}
	
}
