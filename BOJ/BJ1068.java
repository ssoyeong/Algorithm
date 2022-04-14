package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리

public class BJ1068 {
	
	static int n;
	static int root;
	static int del;
	static int[] leaf;
	static ArrayList<Integer>[] list;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		leaf = new int[n];
		list = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			
			if(x == -1) root = i;
			else list[x].add(i);
		}
		
		del = Integer.parseInt(br.readLine());
        
		int result = findLeaves(root) - findLeaves(del);
		int delParent = findDelParent(del);
		if((list[delParent].size() -1) == 0) result++;		// del을 삭제하면 del의 부모가 leaf node가 되는 경우
		
		System.out.println(result);
		
	}
	
	private static int findDelParent(int del) {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < list[i].size(); j++) {
				if(del == list[i].get(j)) return i;
			}
		}
		
		return root;
	}
	
	private static int findLeaves(int x) {
		
		int cnt = 0;	
		if(list[x].size() == 0) return 1;
		for(int i = 0; i < list[x].size(); i++) {
			cnt += findLeaves(list[x].get(i));
		}
		
		return leaf[x] = cnt;
	}
	
}
