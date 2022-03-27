package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// 로또

public class BJ6603 {
	
	static int k;
	static int[] arr;
	static boolean[] visited;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if(k == 0) break;
			arr = new int[k];
			visited = new boolean[k];
			for(int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			backTracking(0, 0);
			bw.newLine();
			bw.flush();
		}

		bw.close();
	}
	
	private static void backTracking(int depth, int idx) throws IOException {
	
		if(depth == 6) {
			printResult();
			return;
		}
		
		for(int i = idx; i < arr.length; i++) {
			visited[i] = true;
			backTracking(depth + 1, i + 1);
			visited[i] = false;
		}	
	}
	
	private static void printResult() throws IOException {
		
		for(int i = 0; i < visited.length; i++) {
			if(visited[i]) {
				bw.write(arr[i] + " ");
			}
		}

		bw.newLine();
	}
}
