import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

// 패션왕 신해빈

public class BJ9375 {
	
	static int tc;
	static int n;
	static HashMap<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t = 0; t < tc; t++) {
			map.clear();
			
			n = Integer.parseInt(br.readLine());
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String type = st.nextToken();
				
				if(map.containsKey(type)) {
					int cnt = map.get(type);
					map.put(type, cnt + 1);
				}
				else {
					map.put(type, 1);
				}
			}
			
			int ans = 1;
			for(String key : map.keySet()) {
				ans = ans * (map.get(key) + 1);
			}
			ans--;
			
			sb.append(ans + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
