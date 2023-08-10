import java.io.*;
import java.util.*;

// 규영이와 인영이의 카드게임

public class SWEA6808 {
	
	static final int SIZE = 18;
	static int tc;
	static int winCntGyu, winCntIn;							// 규영이와 인영이 각각 이긴 횟수
	static boolean[] selected = new boolean[SIZE + 1];		// 규영이가 선택한 카드의 인덱스
	static int[] gyuCards = new int[SIZE / 2];				// 규영이가 선택한 카드에 적힌 수
	static int[] inCards = new int[SIZE / 2];				// 인영이가 선택한 카드에 적힌 수
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= tc; t++) {
			
			// 이긴 횟수 초기화
			winCntGyu = 0;
			winCntIn = 0;
			// selected 초기화
			Arrays.fill(selected, false);
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < SIZE / 2; i++) {
				int x = Integer.parseInt(st.nextToken());
				selected[x] = true;
				gyuCards[i] = x;
			}
		
			// inCards[] 초기화
			int idx = 0;
			for(int i = 1; i <= SIZE; i++) {
				if(!selected[i]) inCards[idx++] = i;
			}
			
			solution();
			sb.append("#" + t + " " + winCntGyu + " " + winCntIn + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void solution() {
		
		Arrays.sort(inCards);
		
		do {
			// inCards[]의 순서가 정해졌으므로, 총점 계산하기
			int totalGyu = 0;
			int totalIn = 0;
			for(int i = 0; i < SIZE / 2; i++) {
				if(gyuCards[i] > inCards[i]) totalGyu += gyuCards[i] + inCards[i];
				else totalIn += gyuCards[i] + inCards[i];
			}
			// 승패 결정하기
			if(totalGyu > totalIn) winCntGyu++;
			else if(totalGyu < totalIn) winCntIn++;
		} while(nextPermutation());
	}
	
	private static boolean nextPermutation() {
		
		int i = SIZE / 2 - 1;
		while(i > 0 && inCards[i - 1] >= inCards[i]) i--;
		if(i == 0) return false;
		
		int j = SIZE / 2 - 1;
		while(inCards[i - 1] >= inCards[j]) j--;
		swap(i - 1, j);
		
		int k = SIZE / 2 - 1;
		while(i < k) swap(i++, k--);
		return true;
	}
	
	private static void swap(int a, int b) {
		int temp = inCards[a];
		inCards[a] = inCards[b];
		inCards[b] = temp;
	}
}
