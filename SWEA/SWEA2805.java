package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 농작물 수확하기

public class SWEA2805 {

	static int t;
	static int n;
	static int[][] arr;
	static int total;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {

			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			total = 0;

			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = line.charAt(j) - '0';
				}
			}

			solution();
			sb.append(String.format("#%d %d\n", tc, total));
		}

		System.out.println(sb.toString());
	}

	private static void solution() {

		int mid = n / 2;

		for (int i = 0; i <= mid; i++) {

			for (int j = mid - i; j <= mid + i; j++) {
				total += arr[i][j];
				if(i != mid) total += arr[n - i - 1][j];
			}
		}

	}
}