package baekjoon; 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 최장 공통 부분 수열

public class BJ9252 {
	
	static String str1;
	static String str2;
	static int[][] arr;
	static int length;
	static char[] common;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		
		length = searchLCS();
		System.out.println(length);
		
		printLCS();

	}
	
	private static int searchLCS() {
		
		arr = new int[str1.length()+1][str2.length()+1];
		
		for(int i = 1; i < arr.length; i++) {
			for(int j = 1; j < arr[0].length; j++) {
				
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					arr[i][j] = arr[i-1][j-1] + 1;
				}
				else {
					arr[i][j] = Math.max(arr[i][j-1], arr[i-1][j]);
				}
			}
		}
		
		return arr[arr.length-1][arr[0].length-1];
	}
	
	private static void printLCS() {
		
		common = new char[length];
		int pos = 0;
		
		int i = arr.length - 1;
		int j = arr[0].length - 1;
		while(pos < length) {
			if(arr[i][j-1] == arr[i-1][j] && arr[i][j] > arr[i][j-1]) {
				common[pos] = str1.charAt(i-1);
				pos++;
				i--;
				j--;
			}
			else if(arr[i][j-1] > arr[i-1][j]) {
				j--;
			}
			else {
				i--;
			}
		}
		
		for(int k = common.length - 1; k >= 0; k--) {
			System.out.print(common[k]);
		}
		System.out.println();
	}
}
