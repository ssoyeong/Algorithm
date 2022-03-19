package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 숨바꼭질 4

public class BJ13913 {
	
	static int n;
	static int k;
	static int[] parent;
	static int[] time;
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int MAX_SIZE;
	static int vertex;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		MAX_SIZE = 2 * Math.max(n, k) + 1;
		parent = new int[MAX_SIZE];
		time = new int[MAX_SIZE];
		
		Arrays.fill(parent, -1);
		
		queue.add(n);
		time[n] = 0;
		parent[n] = -MAX_SIZE; 		// root node는 parent 없음
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			
			if(poll == k) break;
			
			if(poll*2 < MAX_SIZE && parent[poll*2] == -1) {
				queue.add(poll*2);
				time[poll*2] = time[poll] + 1;
				parent[poll*2] = poll;
			}
			if(poll-1 >= 0 && parent[poll-1] == -1) {
				queue.add(poll-1);
				time[poll-1] = time[poll] + 1;
				parent[poll-1] = poll;
			}
			if(poll+1 < MAX_SIZE && poll+1 <= k && parent[poll+1] == -1) {
				queue.add(poll+1);
				time[poll+1] = time[poll] + 1;
				parent[poll+1] = poll;
			}	
			
		}

		bw.write(time[k] + "\n");
		
		Stack<Integer> stack = new Stack<>();
		vertex = k;
		for(int i = 0; i <= time[k]; i++) {
			if(vertex == -1) break;
			stack.push(vertex);
			vertex = parent[vertex];
		}
	
		while(!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}
		
		bw.close();
		
	}
	
	
}
