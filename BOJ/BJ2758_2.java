package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 로또
/* Back Tracking으로 구현하면 백준에서는 시간 초과 발생
DP로 해결하였음 */

public class BJ2758_2 {
	
	static int t;
	static int n;
	static int m;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			cnt = 0;
			backTracking(0, 0);
			
			System.out.println(cnt);
		}
	}
	
	private static void backTracking(int depth, int idx) {
		
		if(depth == n) {
			cnt++;
			return;
		}
		
		if(idx * 2 <= m) {		// isPromising() 조건
			for(int i = idx*2; i <= m; i++) {
				if(depth == 0 && i == 0) continue;
				backTracking(depth + 1, i);
			}
		}
		else return;
		
		
	}
}
