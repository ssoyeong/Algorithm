package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 연산자 끼워넣기

public class BJ14888 {
	
	static int n;
	static int[] num;
	static int[] operator = new int[4];
	static long min = Integer.MAX_VALUE;
	static long max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
			
		}
		
		dfs(num[0], 1);
		System.out.println(max);
		System.out.println(min);
		
	}
	
	private static void dfs(int calc, int idx) {
		if(idx == n) {
			max = Math.max(max, calc);
			min = Math.min(min, calc);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			
			if(operator[i] > 0) {
				
				operator[i]--;
				
				switch(i) {
				
				case 0: dfs(calc + num[idx], idx + 1);
				break;
				case 1: dfs(calc - num[idx], idx + 1);
				break;
				case 2: dfs(calc * num[idx], idx + 1);
				break;
				case 3: dfs(calc / num[idx], idx + 1);
				break;
				
				}
				
				operator[i]++;
			}
		}
	}
	

}
