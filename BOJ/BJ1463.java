package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

// 1로 만들기

public class BJ1463 {
	
	static int x;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		x = Integer.parseInt(br.readLine());
		
		arr = new int[x+1];
		Arrays.fill(arr, x);
		arr[1] = 0;
		
		for(int i = 1; i < x; i++) {
			
			if(i*3 <= x) {
				arr[i*3] = Math.min(arr[i*3], arr[i]+1);
			}
			if(i*2 <= x) {
				arr[i*2] = Math.min(arr[i*2], arr[i]+1);
			}
			if(i+1 <= x) {
				arr[i+1] = Math.min(arr[i+1], arr[i]+1);
			}
		}
		
		
		System.out.println(arr[x]);
	}
}
