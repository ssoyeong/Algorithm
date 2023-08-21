import java.io.*;
import java.util.*;

// 가스관

public class BJ2931 {
    
    static int r, c;
    static char[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ansX, ansY;
    static char ansBlock;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        
        for(int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }

        solution();
        System.out.println(ansX + " " + ansY + " " + ansBlock);
    }

    private static void solution() {

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(board[i][j] == '.') {            // 빈 칸이라면 상하좌우 탐색해보기
                    
                    String openDirection = "";      // 뚫려있는 방향을 담음
                    for(int d = 0; d < 4; d++) {
                        int xx = i + dx[d];
                        int yy = j + dy[d];

                        if(xx < 0 || xx >= r || yy < 0 || yy >= c || board[xx][yy] == '.') continue;
                        char target = board[xx][yy];
                        if(d == 0 && (target == '|' || target == '+' || target == '1' || target == '4' || target == 'M' || target == 'Z')) openDirection = openDirection.concat(d + "");
                        if(d == 1 && (target == '-' || target == '+' || target == '3' || target == '4' || target == 'M' || target == 'Z')) openDirection = openDirection.concat(d + "");
                        if(d == 2 && (target == '|' || target == '+' || target == '2' || target == '3' || target == 'M' || target == 'Z')) openDirection = openDirection.concat(d + "");
                        if(d == 3 && (target == '-' || target == '+' || target == '1' || target == '2' || target == 'M' || target == 'Z')) openDirection = openDirection.concat(d + "");
                    }

                    if(openDirection.length() >= 2) {
                        ansX = i + 1;
                        ansY = j + 1;

                        switch(openDirection) {
                            case "02": ansBlock = '|';
                                        break;
                            case "13": ansBlock = '-';
                                        break;
                            case "12": ansBlock = '1';
                                        break;
                            case "01": ansBlock = '2';
                                        break;
                            case "03": ansBlock = '3';
                                        break;
                            case "23": ansBlock = '4';
                                        break;
                        }

                        if(openDirection.equals("0123")) {
                            char up = board[i + dx[0]][j + dy[0]];
                            char right = board[i + dx[1]][j + dy[1]];
                            char down = board[i + dx[2]][j + dy[2]];
                            char left = board[i + dx[3]][j + dy[3]];
                            
                            // 블필요한 블록이 존재하지 않는다는 조건을 만족하기 위해 M과 Z 사이에 하나의 블록만 있는지 확인하고, 곧바로 M과 Z를 연결하지 않도록 함
                            if((up == 'M' && down == 'Z') || (up == 'Z' && down == 'M')) ansBlock = '-';
                            else if((right == 'M' && left == 'Z') || (right == 'Z' && left == 'M')) ansBlock = '|';
                            // 그러한 경우가 아니라면, + 블록 추가하기
                            else ansBlock = '+';
                        }
                        return;
                    }
                }
            }
        }
    }
}