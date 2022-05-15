package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 덩치

public class BJ7568 {
	
	static int n;
	static ArrayList<Person> arr = new ArrayList<>();
	
	static class Person implements Comparable<Person> {
		
		int weight;
		int height;
		int idx;
		int rank;
		
		Person(int weight, int height, int idx){
			this.weight = weight;
			this.height = height;
			this.idx = idx;
		}

		@Override
		public int compareTo(Person o) {
			
			if(this.weight == o.weight) return this.height - o.height;
			return this.weight - o.weight;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			arr.add(new Person(w, h, i));
		}
		
		Collections.sort(arr);
		
		for(int i = 0; i < n; i++) {
			int cnt = 0;
			for(int j = i+1; j < n; j++) {
				if(arr.get(i).weight == arr.get(j).weight) continue;
				if(arr.get(i).height < arr.get(j).height) cnt++;
			}
			arr.get(i).rank = cnt+1;
		}
		
		arr.sort((o1, o2) -> {
			return o1.idx - o2.idx;
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(arr.get(i).rank).append(" ");
		}
		
		System.out.println(sb.toString());
	}
}
