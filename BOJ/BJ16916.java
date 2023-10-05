import java.io.*;

// 부분 문자열

public class BJ16916 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		int sLen = S.length;
		int pLen = P.length;
		
		// 부분 일치 테이블 만들기
		int[] table = new int[pLen];
		for(int i = 1, j = 0; i < pLen; i++) {
			while(j > 0 && P[i] != P[j]) j = table[j - 1];
			if(P[i] == P[j]) table[i] = ++j;
		}
		
		// 부분 문자열 여부 판단하기
		for(int i = 0, j = 0; i < sLen; i++) {
			while(j > 0 && S[i] != P[j]) j = table[j - 1];
			if(S[i] == P[j]) {
				if(j == pLen - 1) {
					System.out.println(1);
					System.exit(0);
				}
				else j++;
			}
		}
		
		System.out.println(0);
	}

}
