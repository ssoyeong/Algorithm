package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

// 숫자 카드 2

public class BJ10816 {
	
	static int n, m;
	static HashMap<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(x)) {
				map.put(x, 1);
			}
			else {
				int cnt = map.get(x);
				map.remove(x);
				map.put(x, ++cnt);
			}
		}
		
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int x = Integer.parseInt(st.nextToken());
			if(!map.containsKey(x)) {
				sb.append("0 ");
			}
			else {
				sb.append(map.get(x)).append(" ");
			}
		}
		
		System.out.println(sb.toString());
	}

}
