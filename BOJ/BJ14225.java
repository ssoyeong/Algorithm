package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 부분수열의 합

public class BJ14225 {
	
	static int n;
	static int[] num;
	static boolean[] check = new boolean[2000000];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int time = 1; time < Math.pow(2, n); time++) {
			
			long sum = 0;
			for(int digit = 0; digit < n; digit++) {
				
				int x = time & (1 << digit);
				if(x != 0) sum += num[digit];	
			}

			check[(int)sum] = true;
		}
		
		for(int i = 1; i < check.length; i++) {
			if(!check[i]) {
				System.out.println(i);
				break;
			}
		}
	}	

}
