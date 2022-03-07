package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// 팬케이크 뒤집기

public class BJ2759 {
	
	static StringTokenizer st;
	static int test;
	static List<Integer> answerList = new ArrayList<>();
	static int ordered;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		test = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < test; i++) {
			int pancakeNum;
			answerList.clear();
			ordered = 0;
			
			st = new StringTokenizer(br.readLine());
			pancakeNum = Integer.parseInt(st.nextToken());
			int[] pancakes = new int[pancakeNum];
	
			for(int j = 0; j < pancakes.length; j++) {
				pancakes[j] = Integer.parseInt(st.nextToken());
			}
			
			while(!isOrdered(pancakes)) {
				int maxPos = searchMaxPosition(pancakes, ordered);
				
				if(maxPos != 0) {
					flip(pancakes, maxPos);
				}
				
				flip(pancakes, pancakes.length - ordered - 1);
				ordered++;
			}
			
			System.out.print(answerList.size() + " ");
			for(int k = 0; k < answerList.size(); k++) {
				System.out.print(answerList.get(k) + " ");
			}
			System.out.println();
		}
		
	}
	
	
	private static boolean isOrdered(int[] pancakes) {

		int[] pancakesOrdered = new int[pancakes.length];
		int i = 0;
		
		for(int pancake : pancakes) {
			pancakesOrdered[i] = pancake;
			i++;
		}
		
		Arrays.sort(pancakesOrdered);
		return Arrays.equals(pancakes, pancakesOrdered);
	}
	
	
	private static int searchMaxPosition(int[] pancakes, int ordered) {
		
		int maxPos = 0;
		int max = pancakes[0];
		
		for(int i = 0; i < pancakes.length - ordered; i++) {
			if(max < pancakes[i]) {
				max = pancakes[i];
				maxPos = i;
			}
		}
		return maxPos;
	}
	
	
	private static void flip(int[] pancakes, int lastPos) {
		
		int flipLength = lastPos + 1;
		
		for(int i = 0; i < flipLength / 2; i++) {
			int temp = pancakes[i];
			pancakes[i] = pancakes[flipLength - i - 1];
			pancakes[flipLength - i - 1] = temp;
		}
		answerList.add(lastPos + 1);
	}
	
}
