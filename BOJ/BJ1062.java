import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 가르침

public class BJ1062 {

    static int n, k;
    static int ans;
    static int[] words;
    static boolean[] visited = new boolean[26];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 입력 단어들을 비트 마스크로 변환해서 words[]에 담기
        words = new int[n];
        for(int i = 0; i < n; i++) {
            String input = br.readLine();
            words[i] = convertWordToBitMask(input);
        }

        if(k < 5) {
            ans = 0;
        }
        else if(k == 26) {
            ans = n;
        }
        else {
            // visited[]에 a, n, t, i, c 저장하기
            visited['a' - 'a'] = true;
            visited['n' - 'a'] = true;
            visited['t' - 'a'] = true;
            visited['i' - 'a'] = true;
            visited['c' - 'a'] = true;
                
            backTracking(0, 0);        
        }

        System.out.println(ans);
    }

    private static int convertWordToBitMask(String input) {

        char[] chars = input.toCharArray();
        int result = 0;
        
        for(char ch : chars) {
            result = result | (1 << (ch - 'a'));
        }

        return result;
    }

    private static void backTracking(int depth, int start) {

        if(depth == k - 5) {
            // 만들어진 글자 조합으로 읽을 수 있는 단어 개수 구하기
            searchReadableWords();
            return;
        }

        // k - 5 개로 이루어진 조합을 찾아 visited[]에 저장하기
        for(int i = start; i < 26; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            backTracking(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    private static void searchReadableWords() {

        // visited[]를 기반으로 읽을 수 있는 글자 배열을 비트 마스크로 변환하기
        int readableBitMask = convertVisitedToBitMask();

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            // words[i]의 글자가 readableBitMask에 모두 포함되었는지 구분하기
            if((readableBitMask & words[i]) == words[i]) cnt++;
        }

        ans = Integer.max(ans, cnt);
    }

    private static int convertVisitedToBitMask() {

        int result = 0;
        
        for(int i = 0; i < 26; i++) {
            if(visited[i]) {
                result = result | (1 << i);
            }
        }

        return result;
    }
}