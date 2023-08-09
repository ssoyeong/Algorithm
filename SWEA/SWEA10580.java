import java.io.*;
import java.util.*;

// 전봇대

public class SWEA10580 {
	
	static class Line {
		int a;
		int b;
		
		Line(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	static int tc;
	static int n;
	static Line[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();                                                                                          
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new Line[n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[i] = new Line(a, b);
			}
			
			int ans = solution();
			sb.append("#" + t + " " + ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int solution() {
		
		Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.a, o2.a));
		
		int cnt = 0;		
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				if(arr[i].b > arr[j].b) cnt++;
			}
		}
		
		return cnt;
	}

}
