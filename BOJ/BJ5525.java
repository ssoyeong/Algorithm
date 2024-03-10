import java.io.*;

// IOIOI

public class BJ5525 {

    static int n, slen;
    static char[] S;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        slen = Integer.parseInt(br.readLine());
        S = br.readLine().toCharArray();
        System.out.println(solution());
    }

    private static int solution() {

        int cnt = 0;
        int si = 0;
        while(si < slen) {
            if(S[si++] == 'I') {
                int set = 0;
                while(si + 1 < slen) {
                    if(S[si] == 'O' && S[si + 1] == 'I') {
                        set++;
                        si = si + 2;
                    }
                    else break;
                }
                cnt = (set - n + 1) > 0 ? cnt + (set - n + 1) : cnt;
            }
        }

        return cnt;
    }
}
