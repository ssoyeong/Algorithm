package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 생일

public class BJ5635 {
	
	static class Student implements Comparable<Student>{
		
		String name;
		int day;
		int month;
		int year;
		
		Student(String name, int day, int month, int year){
			this.name = name;
			this.day = day;
			this.month = month;
			this.year = year;
		}

		@Override
		public int compareTo(Student o) {
			
			if(this.year == o.year) {
				if(this.month == o.month) {
					return o.day - this.day;
				}
				return o.month - this.month;
			}
			return o.year - this.year;
		}
	}
	
	static int n;
	static Student[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new Student[n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int day = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			
			arr[i] = new Student(name, day, month, year);
		}
		
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0].name).append("\n");
		sb.append(arr[n-1].name).append("\n");
		
		System.out.println(sb.toString());
		
	}

}
