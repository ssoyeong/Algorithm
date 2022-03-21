package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// N-Queen

public class BJ9663 {
	
	static int N;
	static int cols[];
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cols = new int[N+1];
		
		nQueen(0);
		System.out.println(cnt);
	}
	
	private static void nQueen(int depth) {
		
		if(isPromising(depth)) {
			if(depth == N) cnt++;
			else {
				for(int i = 1; i <= N; i++) {
					cols[depth + 1] = i;
					nQueen(depth + 1);
				}
			}
		}	
	}
	
	private static boolean isPromising(int depth) {
		
		int i = 1;
		while(i < depth) {
			if(cols[i] == cols[depth] || Math.abs(cols[i] - cols[depth]) == Math.abs(i - depth)) return false;		// 같은 행 or 대각선인지 판단
			i++;
		}
		
		return true;
	}
	

}
