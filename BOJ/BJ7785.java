package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

// 회사에 있는 사람

public class BJ7785 {
	
	static int n;
	static HashSet<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String cmd = st.nextToken();
			
			if(cmd.equals("enter")) set.add(name);
			else set.remove(name);
		}
		
		List<String> list = new ArrayList<>(set);
		Collections.sort(list, Collections.reverseOrder());
		
		StringBuilder sb = new StringBuilder();
		for(String str : list) {
			sb.append(str).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
