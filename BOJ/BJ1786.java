import java.io.*;
import java.util.*;

// 찾기

public class BJ1786 {
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] T = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();
        int tLen = T.length;
        int pLen = P.length;

        int[] table = new int[pLen];
        for(int i = 1, j = 0; i < pLen; i++) {
            while(j > 0 && P[i] != P[j]) j = table[j - 1];
            if(P[i] == P[j]) table[i] = ++j;
        }

        ArrayList<Integer> pos = new ArrayList<>();
        for(int i = 0, j = 0; i < tLen; i++) {
            while(j > 0 && T[i] != P[j]) j = table[j - 1];
            if(T[i] == P[j]) {
                if(j == pLen - 1) {
                    pos.add(i - j + 1);
                    j = table[j];
                }
                else j++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pos.size() + "\n");
        for(int p : pos) {
            sb.append(p + " ");
        }
        System.out.println(sb.toString());
    }
}
