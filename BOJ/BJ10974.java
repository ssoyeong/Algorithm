import java.io.*;

// 모든 순열

public class BJ10974 {
	
	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		
		StringBuilder sb = new StringBuilder();
		do {
			for(int i = 0; i < n; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
		} while(nextPermutation());
		
		System.out.println(sb.toString());
	}
	
	private static boolean nextPermutation() {
		
		int i = n - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		if(i == 0) return false;
		
		int j = n - 1;
		while(arr[i - 1] >= arr[j]) j--;
		swap(i - 1, j);
		
		int k = n - 1;
		while(i < k) swap(i++, k--);
		return true;
	}
	
	private static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
