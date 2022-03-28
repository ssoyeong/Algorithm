package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 긴 바이토닉 부분 수열

public class BJ11054 {
	
	static int n;
	static int[] arr;
	static int[] dpDown;
	static int[] dpUp;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		result = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < arr.length; i++) {
			
			int a = downSeq(i);
			int b = upSeq(i);
			
			result[i] = a + b - 1;
		}
		
		Arrays.sort(result);
		System.out.println(result[result.length - 1]);
		
	}

	private static int downSeq(int key) {
		
		dpDown = new int[n];
		Arrays.fill(dpDown, 1);
		
		for(int i = key; i >= 0; i--) {
			for(int j = key; j > i; j--) {
				if(arr[i] < arr[j]) {
					dpDown[i] = Math.max(dpDown[i], dpDown[j]+1);
				}
			}
		}
		
		Arrays.sort(dpDown);
		return dpDown[dpDown.length - 1];
	}
	
	private static int upSeq(int key) {
		
		dpUp = new int[n];
		Arrays.fill(dpUp, 1);
		
		for(int i = key; i < arr.length; i++) {
			for(int j = key; j < i; j++) {
				if(arr[i] < arr[j]) {
					dpUp[i] = Math.max(dpUp[i], dpUp[j]+1);
				}
			}
		}
		
		Arrays.sort(dpUp);
		return dpUp[dpUp.length - 1];
	}	

}
