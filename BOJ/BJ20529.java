import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 가까운 세 사람의 심리적 거리

public class BJ20529 {
	
	static int t;
	static int n;
	static int min;
	static int[] types;
	static boolean[] visited;
	static int[] target = new int[3];					// 비교할 세 사람
	static int[][] distanceMatrix = new int[16][16];		// mbti간 거리를 저장하는 매트릭스
	
	public static void main(String[] args) throws IOException {
		
		makeDistanceMatrix();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			types = new int[n];
			visited= new boolean[n];
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				types[j] = convertStringToInteger(st.nextToken());
			}
			
			int ans = 0;
			if(n <= 32) ans = solution();
			sb.append(ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void makeDistanceMatrix() {
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				if(i != j) {
					
					// i와 j를 이진수로 나타내었을 때, 각 자릿수에 대해 다르다면 1 증가
					int dist = 0;
					for(int k = 0; k < 4; k++) {
						if((i & (1 << k)) != (j & (1 << k))) dist++;
					}
					
					distanceMatrix[i][j] = dist;
				}
			}
		}
	}
	
	private static int convertStringToInteger(String input) {
		
		int value = 0;
		
		if(input.charAt(0) == 'I') value += Math.pow(2, 3);
		if(input.charAt(1) == 'N') value += Math.pow(2, 2);
		if(input.charAt(2) == 'F') value += Math.pow(2, 1);
		if(input.charAt(3) == 'J') value += Math.pow(2, 0);
		
		return value;
	}
	
	private static int solution() {
		
		min = Integer.MAX_VALUE;
		Arrays.fill(visited, false);
		
		backTracking(0, 0);
		return min;
	}
	
	private static void backTracking(int depth, int idx) {
		
		if(depth == 3) {
			calculateDistance();
			return;
		}
		
		for(int i = idx; i < n; i++) {
			
			if(visited[i]) continue;
			
			visited[i] = true;
			target[depth] = types[i];
			
			backTracking(depth + 1, i + 1);
			
			visited[i] = false;
		}
	}
	
	private static void calculateDistance() {
		
		int total = 0;
		for(int i = 0; i < 3; i++) {
			total += distanceMatrix[target[i % 3]][target[(i + 1) % 3]];
		}	
		
		min = Integer.min(min, total);
	}
}
