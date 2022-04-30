package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 저울

public class BJ2437 {
	
	static int n;
	static int total = 1;		// 최소 추의 무게인 1로 초기화
	static int[] weight;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		weight = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(weight);
		
		for(int i = 0; i < weight.length; i++) {
			
			if(total < weight[i]) break;
			total += weight[i];
		}
		
		
		System.out.println(total);
	
	}

}
