package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 스타트와 링크

public class BJ14889 {
	
	static int n;
	static int[][] sk;
	static boolean[] visited;			// true라면 teamStart
	static long min = Long.MAX_VALUE;
	static int[] teamStart;				// teamStart인 사람의 번호 저장
	static int[] teamLink;				// teamLink인 사람의 번호 저장
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sk = new int[n][n];
		visited = new boolean[n];
		teamStart = new int[n/2];
		teamLink = new int[n/2];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				sk[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTracking(0, 0);
		
		System.out.println(min);
	}
	
	private static void backTracking(int depth, int idx) {
		if(depth == n/2) {
			divideTeam();
			if(min == 0) {
				System.out.println(min);
				System.exit(0);
			}
			return;
		}
		
		for(int i = idx; i < n; i++) {
			visited[i] = true;
			backTracking(depth + 1, i + 1);
			visited[i] = false;
			if(depth == 0) break;
		}
	}
	
	private static void divideTeam() {		// visited를 통해 각 팀에 속한 사람의 번호를 넣어준다
		
		int numStart = 0;
		int numLink = 0;
		for(int i = 0; i < n; i++) {
			if(visited[i]) {
				teamStart[numStart] = i;
				numStart++;
			}
			else {
				teamLink[numLink] = i;
				numLink++;
			}
		}
		
		calculate();
		
	}
	
	private static void calculate() {		// 각 탐의 능력치 합 구하기
		
		long totalStart = 0;
		long totalLink = 0;
		for(int i = 0; i < teamStart.length; i++) {
			for(int j = 0; j < teamStart.length; j++) {
				if(i == j) continue;
				totalStart += sk[teamStart[i]][teamStart[j]];
			}
		}
		
		for(int i = 0; i < teamLink.length; i++) {
			for(int j = 0; j < teamLink.length; j++) {
				if(i == j) continue;
				totalLink += sk[teamLink[i]][teamLink[j]];
			}
		}
		
		long sub = Math.abs(totalStart - totalLink);
		if(sub < min) min = sub;
		
	}

}
