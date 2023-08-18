import java.io.*;
import java.util.*;

// 주사위 윷놀이

public class BJ17825 {
    
    private static class Node {
        int score;
        int next;
        int nextBlue;

        Node(int score, int next, int nextBlue) {
            this.score = score;
            this.next = next;
            this.nextBlue = nextBlue;
        }
    }
    static final int PIECENUM = 4;
    static final int TURNNUM = 10;
    static int ans;
    static int[] turns = new int[TURNNUM];
    static int[] order = new int[TURNNUM];
    static int[] position = {0, 0, 0, 0};
    static Node[] board = new Node[33];

    public static void main(String[] args) throws IOException {
    	
    	makeBoard();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < TURNNUM; i++) {
            turns[i] = Integer.parseInt(st.nextToken());
        }

        makeOrder(0, 0);
        System.out.println(ans);
    }

    private static void makeBoard() {
        
        board[0] = new Node(0, 1, 0);
        for(int i = 1; i < 20; i++) {
            board[i] = new Node(i * 2, i + 1, 0);
        }
        board[20] = new Node(40, 32, 0); board[32] = new Node(0, 99, 0);

        board[5] .nextBlue = 21;
        board[21] = new Node(13, 22, 0); board[22] = new Node(16, 23, 0); board[23] = new Node(19, 29, 0);
        
        board[10] .nextBlue = 24;
        board[24] = new Node(22, 25, 0); board[25] = new Node(24, 29, 0);

        board[15] .nextBlue = 26;
        board[26] = new Node(28, 27, 0); board[27] = new Node(27, 28, 0); board[28] = new Node(26, 29, 0);

        board[29] = new Node(25, 30, 0); board[30] = new Node(30, 31, 0); board[31] = new Node(35, 20, 0);
    }

    private static void makeOrder(int depth, int total) {
        
        if(depth == TURNNUM) {
            ans = Integer.max(ans, total);
            return;
        }

        for(int i = 0; i < PIECENUM; i++) {
            
            if(position[i] == 32) continue;
            order[depth] = i;

            int[] tempPosition = new int[PIECENUM];
            for(int j = 0; j < PIECENUM; j++) {
                tempPosition[j] = position[j];
            }

            int score = movePiece(turns[depth], i);
            if(score == -1) continue;
            makeOrder(depth + 1, total + score);

            for(int j = 0; j < PIECENUM; j++ ) {
                position[j] = tempPosition[j];
            }
        }
    }

    private static int movePiece(int num, int piece) {      // num 만큼 piece 말을 이동시키기

        int pos = position[piece];

        // 파란색 칸이라면
        if(board[pos].nextBlue != 0) {
            pos = board[pos].nextBlue;
            num--;
        }

        while(num > 0) {
            pos = board[pos].next;
            num--; 
            if(pos == 32) {
            	position[piece] = 32;
            	return 0;
            }
        }
        
        // 말이 이동을 마쳤는데 해당 위에 다른 말이 있는지 확인
        for(int i = 0; i < 4; i++) {
            if(i == piece) continue;
            if(position[i] == pos) return -1;
        }
        position[piece] = pos;
    
        return board[pos].score;
    }

}
