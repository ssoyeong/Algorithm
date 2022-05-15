package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 수 이어 쓰기 1

public class BJ1748 {
	
	static int n;
	static int size;
	static long answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		size = input.length();
		n = Integer.parseInt(input);
		
		for(int i = 1; i < size; i++) {
			answer += i * 9 * Math.pow(10, i-1);
		}
		
		answer += size * (n - Math.pow(10, size-1) + 1);
		System.out.println(answer);
	}

}
