import java.io.*;

// 백설 공주와 일곱 난쟁이

public class BJ3040 {
	
	static final int SIZE = 9;
	static int[] numbers = new int[SIZE];
	static int[] flag = new int[SIZE];
	
	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < SIZE; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = SIZE - 1; i >= SIZE - 7; i--) {
			flag[i] = 1;
		}
		
		do {
			if(calculateTotal() == 100) {
				for(int i = 0; i < SIZE; i++) {
					if(flag[i] == 1) sb.append(numbers[i] + "\n");
				}
				
				break;
			}
		} while(nextCombination());
		
		System.out.println(sb.toString());
	}
	
	private static int calculateTotal() {
		
		int total = 0;
		
		for(int i = 0; i < SIZE; i++) {
			int x = flag[i] & 1;
			if(x != 0) total+= numbers[i];
		}
		return total;
	}
	
	private static boolean nextCombination() {
		
		int i = SIZE - 1;
		while(i > 0 && flag[i - 1] >= flag[i]) i--;
		if(i == 0) return false;
		
		int j = SIZE - 1;
		while(flag[i - 1] >= flag[j]) j--;
		swap(i - 1, j);
		
		int k = SIZE - 1;
		while(i < k) swap(i++, k--);
		return true;
	}
	
	private static void swap(int a, int b) {
		int tmp = flag[a];
		flag[a] = flag[b];
		flag[b] = tmp;
	}
} 

