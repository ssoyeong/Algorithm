package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 숫자놀이

public class BJ1755 {
	
	static class Number implements Comparable<Number> {
		
		int num;
		String str;
		
		Number(int num, String str){
			this.num = num;
			this.str = str;
		}
		
		@Override
		public int compareTo(Number o) {
			return this.str.compareTo(o.str);
		}
	}
	
	static int m;
	static int n;
	static ArrayList<Number> arr = new ArrayList<>();
	static String[] str = {"zero", "one", "two", "three", "four",
							"five", "six", "seven", "eight", "nine"};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		for(int i = m; i <= n; i++) {
			arr.add(new Number(i, convert(i)));
		}
		
		Collections.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.size(); i++) {
			if(i%10 == 0 && i != 0) sb.append("\n");
			sb.append(arr.get(i).num).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	private static String convert(int x) {
		
		String converted = "";
		
		if(x < 10) {
			converted = str[x];
		}
		else {
			converted = str[x/10];
			converted = converted.concat(str[x%10]);
		}
		
		return converted;
	}
}
