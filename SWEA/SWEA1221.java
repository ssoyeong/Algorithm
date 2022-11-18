package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// GNS

public class SWEA1221 {
	
	static int t;
	static int n;
	static int[] cnt;
	static String[] words = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= t; tc++) {
			
			cnt = new int[10];
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				String word = st.nextToken();
				
				switch(word) {
				case "ZRO": cnt[0]++;
							break;
				case "ONE": cnt[1]++;
							break;
				case "TWO": cnt[2]++;
							break;
				case "THR": cnt[3]++;
							break;
				case "FOR": cnt[4]++;
							break;
				case "FIV": cnt[5]++;
							break;
				case "SIX": cnt[6]++;
							break;
				case "SVN": cnt[7]++;
							break;
				case "EGT": cnt[8]++;
							break;
				default: cnt[9]++;
				}
			}
			
			sb.append("#" + tc + "\n");
			for(int i = 0; i < 10; i++) {
				
				for(int j = 0; j < cnt[i]; j++) {
					sb.append(words[i] + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
