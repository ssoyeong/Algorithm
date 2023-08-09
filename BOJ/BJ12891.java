import java.io.*;
import java.util.*;

// DNA 비밀번호

public class BJ12891 {

    static int s, p;
    static int ans;
    static int start, end;
    static char[] dna;
    static int[] numOfDna = new int[4]; 		// dna에 포함된 각 문자의 개수
    static int[] included = new int[4]; 		// 포함되어야 할 각 문자의 개수
    static int[] tempIncluded = new int[4];
    static HashMap<Character, Integer> converter = new HashMap<>();

    public static void main(String[] args) throws IOException {
    	
    	converter.put('A', 0);
    	converter.put('C', 1);
    	converter.put('G', 2);
    	converter.put('T', 3);
    	

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        String line = br.readLine();
        dna = line.toCharArray();
        for (int i = 0; i < s; i++) {
            numOfDna[converter.get(dna[i])]++;
        }

        st = new StringTokenizer(br.readLine());

        boolean isInvalid = false;
        for (int i = 0; i < 4; i++) {
            included[i] = Integer.parseInt(st.nextToken());
            if (numOfDna[i] < included[i]) { 					// 포함되어야 할 문자의 개수만큼 dna에 포함되어 있지 않을 경우
                ans = 0;
                isInvalid = true;
                break;
            }
        }

        if (!isInvalid) solution();
        System.out.println(ans);
    }

    private static void solution() {

        int start = 0;
        int end = p;
         
        // 첫번째 부분 문자열
        for (int i = start; i < end; i++) {
        	included[converter.get(dna[i])]--;
        }

        if(isValid()) ans++;
        start++;
        end++;
        
        // 나머지 부분 문자열
        while(end <= s) {
        	
        	included[converter.get(dna[start - 1])]++;
        	included[converter.get(dna[end - 1])]--;

        	if(isValid()) ans++;
        	start++;
        	end++;
        }
    }
    
    private static boolean isValid() {
    	
    	for(int i = 0; i < 4; i++) {
    		if(included[i] > 0) return false;
    	}
    	return true;
    }

}
