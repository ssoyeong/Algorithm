package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Deque

public class BJ10866 {
	
	static ArrayList<Integer> deque = new ArrayList<>();
	static int n;
	static String command;
	static int output;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			command = st.nextToken();
			
			switch(command) {
			case "push_front":
				push_front(Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				push_back(Integer.parseInt(st.nextToken()));
				break;	
			case "pop_front":
				pop_front();
				break;	
			case "pop_back":
				pop_back();
				break;	
			case "size":
				size();
				break;	
			case "empty":
				empty();
				break;	
			case "front":
				front();
				break;	
			case "back":
				back();
				break;	
			}
			
			
		}
	}
	
	private static void push_front(int val) {
		deque.add(0, val);
	}
	
	private static void push_back(int val) {
		deque.add(val);
	}
	
	private static void pop_front() {
		if(deque.isEmpty()) System.out.println("-1");
		else System.out.println(deque.remove(0));
	}
	
	private static void pop_back() {
		if(deque.isEmpty()) System.out.println("-1");
		else System.out.println(deque.remove(deque.size()-1));
	}
	
	private static void size() {
		System.out.println(deque.size());
	}
	
	private static void empty() {
		if(deque.isEmpty()) System.out.println("1");
		else System.out.println("0");
	}
	
	private static void front() {
		if(deque.isEmpty()) System.out.println("-1");
		else System.out.println(deque.get(0));
	}
	
	private static void back() {
		if(deque.isEmpty()) System.out.println("-1");
		else System.out.println(deque.get(deque.size()-1));
	}
}
