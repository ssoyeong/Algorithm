package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

//에라토스테네스의 체

public class BJ2960 {
	
	static int n;
	static int k;
	static int cnt;
	static boolean[] num;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		num = new boolean[n+1];
			
		for(int i = 2; i <= n; i++) {
			for(int j = i; j <= n; j+=i) {		// i의 배수 체크
				
				if(!num[j]) {
					num[j] = true;
					cnt++;
				}
				
				if(cnt == k) {
					System.out.println(j);
					System.exit(0);
				}
			}
		}
		
	}
	

}
