package a0801;

// 스위치 켜고 끄기

import java.io.*;
import java.util.*;

public class BJ1244 {
	
	static int numOfSwitch, numOfStudent;
	static boolean[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numOfSwitch = Integer.parseInt(br.readLine());
		arr = new boolean[numOfSwitch + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= numOfSwitch; i++) {
			if(st.nextToken().equals("1")) arr[i] = true;
		}
		
		numOfStudent = Integer.parseInt(br.readLine());
		for(int i = 0; i < numOfStudent; i++) {
			st = new StringTokenizer(br.readLine());
			
			int sex = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			changeSwitch(sex, num);
		}
		
		// 출력하기
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= numOfSwitch; i++) {
			if(arr[i]) sb.append("1 ");
			else sb.append("0 ");
			
			if(i % 20 == 0) sb.append("\n");
		}
		sb.append("\n");
		System.out.println(sb.toString());
	}
	
	private static void changeSwitch(int sex, int num) {
		
		// 남학생인 경우
		if(sex == 1) {
			for(int i = 1; i <= numOfSwitch; i++) {
				if(i % num == 0) {
					arr[i] = !arr[i];
				}
			}
		}
		// 여학생인 경우
		else {
			arr[num] = !arr[num];
			
			int preIdx = num - 1;
			int postIdx = num + 1;
			
			while(true) {
				
				if(preIdx < 1 || postIdx > numOfSwitch) break;
				if(arr[preIdx] != arr[postIdx]) break;
				
				arr[preIdx] = !arr[preIdx];
				arr[postIdx] = !arr[postIdx];
				preIdx--;
				postIdx++;
			}
		}
	}
}
