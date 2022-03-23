package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;

// 일곱 난쟁이

public class BJ2309 {
	
	static Integer[] arr = new Integer[9];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		Loop1:
		for(int i = 0; i < 8; i++) {
			for(int j = i+1; j < 9; j++) {
				int sum = sumHeights(i, j);
				if(sum == 100) {
					printResult(i, j);
					break Loop1;
				}
			}
		}
	}
	
	private static int sumHeights(int a, int b) {		// a, b번 째 제외하고 키의 합을 구함
		
		int total = 0;
		for(int i = 0; i < arr.length; i++) {
			if(i != a && i != b) {
				total += arr[i];
				if(total > 100) break;
			}
		}
		return total;
	}
	
	private static void printResult(int a, int b) {
		
		for(int i = arr.length - 1; i >= 0; i--) {
			if(i != a && i != b) System.out.println(arr[i] + " ");
		}
		
		System.out.println();
	}
	
}
