package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 부분 수열의 합  

public class SWEA2871 {
	
	static int t;
	static int n, k;
	static int[] arr;
	static int cnt;

	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= t; tc++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new int[n];
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			dfs(0, 0, 0);
			sb.append(String.format("#%d %d\n", tc, cnt));
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int level, int idx, int sum) {
		
		for(int i = idx; i < n; i++) {
			
			if(sum + arr[i] < k) dfs(level + 1, i + 1, sum + arr[i]);
			else if(sum + arr[i] == k) {
				cnt++;
			}
			else return;
		}
	}

}
