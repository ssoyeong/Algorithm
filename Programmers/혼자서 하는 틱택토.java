import java.util.*;

class Solution {
    
    static char[][] map = new char[3][3];
    static int lineO;
    static int lineX;
    static boolean[][] line = new boolean[8][2];
    public int solution(String[] board) {
        
        int cntO = 0;
        int cntX = 0;
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'O') cntO++;
                else if(map[i][j] == 'X') cntX++;
            }
        }
        
        if(cntO == 0 && cntX == 0) return 1;
        if(cntO == 0 && cntX == 1) return 0;
        if(Math.abs(cntO - cntX) >= 2) return 0;
        
        // 가로 체크
        for(int i = 0; i < 3; i++) {
            if(map[i][0] != '.') {
                if(map[i][1] == map[i][0] && map[i][2] == map[i][0]) {
                    if(map[i][0] == 'O') {
                        lineO++;
                        line[i][0] = true;
                    }
                    else {
                        lineX++;
                        line[i][1] = true;
                    }
                }
            }
        }
        // 세로 체크
        for(int i = 0; i < 3; i++) {
            if(map[0][i] != '.') {
                if(map[1][i] == map[0][i] && map[2][i] == map[0][i]) {
                    if(map[0][i] == 'O') {
                        lineO++;
                        line[i + 3][0] = true;
                    }
                    else {
                        lineX++;
                        line[i + 3][1] = true;
                    }
                }
            }
        }
        // 대각선 체크
        if(map[0][0] != '.') {
            if(map[1][1] == map[0][0] && map[2][2] == map[0][0]) {
                    if(map[0][0] == 'O') {
                        lineO++;
                        line[6][0] = true;
                    }
                    else {
                        lineX++;
                        line[6][1] = true;
                    }
            }
        }
        if(map[0][2] != '.') {
            if(map[1][1] == map[0][2] && map[2][0] == map[0][2]) {
                    if(map[0][2] == 'O') {
                        lineO++;
                        line[6][0] = true;
                    }
                    else {
                        lineX++;
                        line[6][1] = true;
                    }
            }
        }
        
        if(lineO == 0 && lineX == 0) return 1;
        if(lineO == 1 && lineX == 0 && cntO > cntX) return 1;
        if(lineO == 0 && lineX == 1 && cntO == cntX) return 1;
        
        if(lineO == 2 && lineX == 0 && cntO == 5 && cntX == 4) {
            for(int i = 0; i < 3; i++) {
                for(int j = 3; j < 6; j++) {
                    if(line[i][0] && line[j][0]) return 1;
                }
                if(line[6][0] && line[i][0]) return 1;
                if(line[6][0] && line[i + 3][0]) return 1;
                if(line[7][0] && line[i][0]) return 1;
                if(line[7][0] && line[i + 3][0]) return 1; 
            }
            
            if(line[6][0] && line[7][0]) return 1;
        }
        
        return 0;
    }

}
