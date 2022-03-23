package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 암호 만들기

public class BJ1759 {
	
	static int L;
	static int C;
	static char[] arr;
	static int length;
	static char[] output;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		output = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		backTracking(0, 0);
	}
	
	private static void backTracking(int length, int idx) {
		
		if(length == L) {
			if(isValid()) {
				System.out.println(output);
				return;
			}
		}
		
		if(idx >= arr.length || length >= output.length) return;
		
		for(int i = idx; i < C; i++) {		// idx부터 시작하므로 현위치보다 작은 알파벳은 탐색 제외
			output[length] = arr[i];	
			backTracking(length+1, i+1);
		}

	}
	
	private static boolean isValid() {
		
		int vowel = 0;
		int cons = 0;
		
		for(int i = 0; i < output.length; i++) {
			if(output[i] == 'a' || output[i] == 'e' || output[i] == 'i' || output[i] == 'o' || output[i] == 'u') {
				vowel++;
			}
			else cons++;
			
			if(vowel >= 1 && cons >= 2) return true;
		}
		return false;
	}

}
