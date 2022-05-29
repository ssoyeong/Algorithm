package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 요세푸스 문제

public class BJ1158 {
	
	static int n;
	static int k;
	static Queue<Integer> queue = new LinkedList<Integer>();
	static Queue<Integer> answer = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= n; i++) {
			queue.add(i);
		}
		
		int i = 1;
		while(!queue.isEmpty()) {
			
			int poll = queue.poll();
			if(i%k == 0) answer.add(poll);
			else queue.add(poll);
			
			i++;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while(!answer.isEmpty()) {
			int poll = answer.poll();
			if(answer.size() == 0) sb.append(poll).append(">");
			else sb.append(poll).append(", ");
		}
		
		System.out.println(sb.toString());
	}

}
