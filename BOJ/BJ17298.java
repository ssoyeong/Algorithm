package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// 오큰수

public class BJ17298 {
	
	static int n;
	static Stack<Integer> stack = new Stack<>();
	static int[] arr;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		answer = new int[n];
		
		Arrays.fill(answer, -1);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			
			while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {		// stack 안 원소들에 대해 오른쪽 수보다 작을 경우
				answer[stack.pop()] = arr[i];
			}
			
			stack.push(i);		// stack에 index를 담는다
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(answer[i] + " ");
		}
		
		System.out.println(sb.toString());
	}

}
