package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// 나이순 정렬

class Member implements Comparable<Member> {
	
	int age;
	String name;
	int order;
	
	Member(int age, String name, int order){
		
		this.age = age;
		this.name = name;
		this.order = order;
	}
	
	@Override
	public int compareTo(Member o) {
		
		if(this.age == o.age) return this.order - o.order;
		return this.age - o.age;
	}
	
	@Override
	public String toString() {
		return age + " " + name;
	}
}

public class BJ10814 {
	
	private static int n;
	private static Member[] memberList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		memberList = new Member[n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());			
			memberList[i] = new Member(Integer.parseInt(st.nextToken()), st.nextToken(), i+1);
		}
		
		Arrays.sort(memberList);
	
		for(Member mem : memberList) {
			System.out.println(mem);
		}
		
	}
}
