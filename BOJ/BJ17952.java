import java.io.*;
import java.util.*;

// 과제는 끝나지 않아!

public class BJ17952 {
	static int n;
	static int total;		// 점수 합계
	static ArrayDeque<int[]> stack = new ArrayDeque<>();	// 점수와 남은 시간을 담는 스택. 가장 최근에 하던 업무를 해야하므로 스택 사용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("0")) {				// (1) 0이라면
				if(!stack.isEmpty()) {
					int[] pop = stack.pop();				// 스택에서 최근 하던 일을 꺼내고
					if(pop[1] == 1) {						// (1-1) 1분 남은 일이라면, 마칠 수 있으므로 total에 점수를 더함
						total += pop[0];
					}
					else {									// (1-2) 1분 남지 않은 일이라면, 스택에 1분 일한 시간을 빼고 추가함
						stack.addFirst(new int[] {pop[0], pop[1] - 1});
					}
				}
			}
			else {											// (2) 0이 아니라면
				int a = Integer.parseInt(st.nextToken());	// 새로운 업무를 받고
				int t = Integer.parseInt(st.nextToken());
				if(t == 1) {								// (2-1) 1분짜리 일이라면, 마치고 total에 점수 더함
					total += a;
				}
				else {										// (2-2) 1분짜리 일이 아니라면, 스택에 1분 일한 시간을 빼고 추가함
					stack.addFirst(new int[] {a, t - 1});
				}
			}
		}
		
		System.out.println(total);
	}

}
