// 2021 카카오 채용연계형 인턴십 - 거리두기 확인하기

class Solution {
    
    static final int size = 5;
    static boolean flag;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] answer = {1, 1, 1, 1, 1};
    static char[][] board;
    static boolean[][] visited;
    
    public int[] solution(String[][] places) {
        
        for(int tc = 0; tc < size; tc++) {
            
            flag = false;
            board = new char[size][size];
            visited = new boolean[size][size];
            
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    board[i][j] = places[tc][i].charAt(j);
                }
            }
            
            testCase:
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    
                    if(board[i][j] == 'P') {
                        visited[i][j] = true;
                        dfs(i, j, 0);
                        if(flag) {
                            answer[tc] = 0;
                            break testCase;
                        }
                    }
                }
            }
        }
         
        return answer;
    }
    
    private static void dfs(int x, int y, int depth) {
        
        if(depth == 2 || flag == true) return;
        for(int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            
            if(xx < 0 || xx >= size || yy < 0 || yy >= size) continue;
            if(visited[xx][yy]) continue;
            if(board[xx][yy] == 'P') {
                flag = true;
                return;
            }
            if(board[xx][yy] == 'O') {
                visited[xx][yy] = true;
                dfs(xx, yy, depth + 1);
            }
        }
        visited[x][y] = false;
    }
    
}
