package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 최소 편집

public class BJ15483 {
	
	static String str1;
	static String str2;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		
		arr = new int[str1.length()+1][str2.length()+1];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				
				if(i == 0) {
					arr[i][j] = j;
				}
				else if(j == 0) {
					arr[i][j] = i;
				}
				else {
					if(str1.charAt(i-1) == str2.charAt(j-1)) {
						arr[i][j] = arr[i-1][j-1];
					}
					else {
						int myMin = Math.min(arr[i][j-1], arr[i-1][j]);
						arr[i][j] = Math.min(myMin, arr[i-1][j-1]) + 1;
					}
				}
			}
		}
		
		System.out.println(arr[arr.length-1][arr[0].length-1]);
	}

}
