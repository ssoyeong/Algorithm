package a0803;

// 도영이가 만든 맛있는 음식

import java.io.*;
import java.util.*;

public class BJ2961 {

    static int n;
    static int min = Integer.MAX_VALUE;
    static int[] arrS;
    static int[] arrB;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arrS = new int[n];
        arrB = new int[n];
        visited = new boolean[n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st  = new StringTokenizer(br.readLine());
            arrS[i] = Integer.parseInt(st.nextToken());
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(0, 0, 1, 0);
        System.out.println(min);
    }

    private static void backTracking(int depth, int idx, int totalS, int totalB) {

        if(depth == n) return;

        for(int i = idx; i < n; i++) {
            if(visited[i]) continue;

            visited[i] = true;

            int newTotalS = totalS * arrS[i];
            int newTotalB = totalB + arrB[i];
            min = Integer.min(min, Math.abs(newTotalS - newTotalB));

            backTracking(depth + 1, i + 1, newTotalS, newTotalB);
            visited[i] = false;
        }
    }
}
