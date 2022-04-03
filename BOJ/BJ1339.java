package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;

// 단어 수학

public class BJ1339 {
	
	static int n;
	static ArrayList<String> word = new ArrayList<>();
	static Integer[] convert = new Integer[26];
	static long total;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			StringBuffer sb = new StringBuffer(br.readLine());
			word.add(sb.reverse().toString());			// 자릿수를 맞추기 위해 역순 저장
		}
	

		// Solution
		Arrays.fill(convert, 0);
		for(int idx = 0; idx < n; idx++) {
			for(int digit = 0; digit < word.get(idx).length(); digit++) {
				char ch = word.get(idx).charAt(digit);
				int idxCh = ch - 65;
				
				convert[idxCh] += (int)Math.pow(10, digit);		// digit에 따라 10^n 곱해준다.
			}
		}

		Arrays.sort(convert, Collections.reverseOrder());		// 내림차순 정렬

		int num = 9;
		for(int val : convert) {
			if(val == 0) break;
			total += num * val;			// 가장 큰 숫자인 9부터 곱해서 총합을 구한다.
			num--;
		}
		
		System.out.println(total);
	}

}
