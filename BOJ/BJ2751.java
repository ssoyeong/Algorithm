package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

// 수 정렬하기 2

public class BJ2751 {
	
	static int n;
	static ArrayList<Integer> arr = new ArrayList<>();
	 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(arr);
		
		for(int x : arr) {
			sb.append(x).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
