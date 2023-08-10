import java.io.*;
import java.util.*;

// [모의 SW 역량테스트] 요리사

public class SWEA4012 {
	
	static int tc;
	static int n;
	static int ans;
	static long time, timeCnt;
	static boolean flag;		// backTracking 탐색을 종료할 플래그
	static boolean[] selected;
	static int[] arrA;
	static int[] arrB;
	static int[][] score;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= tc; t++) {
			
			ans = Integer.MAX_VALUE;
			
			n = Integer.parseInt(br.readLine());
			selected = new boolean[n];
			arrA = new int[n / 2];
			arrB = new int[n / 2];
			score = new int[n][n];
			
			// n개의 원소를 n/2개씩 두 개의 그룹으로 나누는 경우의 수
			time = (factorial(n) / 2) / (factorial(n / 2) * factorial(n / 2));
			timeCnt = 0;
			flag = false;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			backTracking(0, 0);
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}
	
	private static long factorial(long x) {
		
		if(x == 1) return x;
		return x * factorial(x - 1);
	}
	
	private static void backTracking(int depth, int idx) {
		
		if(flag) return;
		
		if(depth == n / 2) {
			
			int cnt = 0;
			for(int i = 0; i < n; i++) {
				if(!selected[i]) arrB[cnt++] = i;
			}
			int totalA = calculateScore(arrA);
			int totalB = calculateScore(arrB);
			ans = Integer.min(ans, Math.abs(totalA - totalB));
			
			timeCnt++;
			if(timeCnt == time) flag = true;
			return;
		}
		
		for(int i = idx; i < n; i++) {
			arrA[depth] = i;
			selected[i] = true;
			backTracking(depth + 1, i + 1);
			selected[i] = false;
		}
	}
	
	private static int calculateScore(int[] arr) {
		int total = 0;
		for(int i = 0; i < n / 2; i++) {
			for(int j = i + 1; j < n / 2; j++) {
				total += score[arr[i]][arr[j]];
				total += score[arr[j]][arr[i]];
			}
		}
		return total;
	}

}
