package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 타임머신

public class BJ11657 {
	
	static int numV;
	static int numE;
	static ArrayList<Node>[] adj;
	static Long[] vertex;			// Integer 사용하면 underflow 발생
	static class Node {
		
		int to;
		int value;
		
		Node(int to, int value){
			this.to = to;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numV = Integer.parseInt(st.nextToken());
		numE = Integer.parseInt(st.nextToken());
		adj = new ArrayList[numV+1];
		vertex = new Long[numV+1];
		
		for(int i = 1; i <= numV; i++) {
			adj[i] = new ArrayList<Node>();
		}
		
		for(int i = 0; i < numE; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			adj[x].add(new Node(y, val));
		}
		
		boolean isNegativeCycle = bellmanFord();
		if(isNegativeCycle) {
			System.out.println("-1");
		}
		else {
			for(int i = 2; i <= numV; i++) {
				if(vertex[i] == Long.MAX_VALUE) {
					System.out.println("-1");
				}
				else {
					System.out.println(vertex[i]);
				}
			}
		}
	}
	
	private static boolean bellmanFord() {
		
		Arrays.fill(vertex, Long.MAX_VALUE);
		vertex[1] = 0L;
		
		for(int i = 0; i < numV - 1; i++) {
			for(int j = 1; j <= numV; j++) {
				if(vertex[j] != Long.MAX_VALUE) {
					for(Node n : adj[j]) {
						if(vertex[n.to] > vertex[j] + n.value) {
							vertex[n.to] = vertex[j] + n.value;
						}
					}
				}
			}
		}
		
		for(int j = 1; j <= numV; j++) {
			if(vertex[j] != Long.MAX_VALUE) {
				for(Node n : adj[j]) {
					if(vertex[n.to] > vertex[j] + n.value) {
						return true;
					}
				}
			}
		}
		
		return false;
		
	}
}
