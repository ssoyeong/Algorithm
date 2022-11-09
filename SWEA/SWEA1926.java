import java.io.BufferedReader;
import java.io.InputStreamReader;

// 간단한 369게임

class SWEA1926 {
	
    static int n;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            String output = solution(Integer.toString(i));
            sb.append(output + " ");
        }
        
        System.out.println(sb.toString());
    }
    
    private static String solution(String str) {
    	
        String result = "";
        int cnt = 0;
        for(int i = 0; i < str.length(); i++) {
        	
            int x = str.charAt(i) - '0';

            if(x%3 == 0 && x != 0) {
                cnt++;
            }
        }
        
        if(cnt == 0) result = str;
        else {
        	for(int i = 0; i < cnt; i++) result = result.concat("-");
        }
        
        return result;
    }
}