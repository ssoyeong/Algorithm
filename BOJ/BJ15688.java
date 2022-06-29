package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

// 수 정렬하기 5

public class BJ15688 {
	
	static int n;
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			list.add(x);
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(int x : list) {
			sb.append(x).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
