package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 최대 동전 개수

public class BJ11047 {
	
	static int n;
	static int total;
	static int[] coin;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		total = Integer.parseInt(st.nextToken());
		
		coin = new int[n];
		for(int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = coin.length - 1; i >= 0; i--) {
			if(coin[i] <= total) {
				cnt += total / coin[i];
				total -= (total / coin[i]) * coin[i];
			}
		}
		
		System.out.println(cnt);
		
	}

}
