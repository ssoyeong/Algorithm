import java.io.*;
import java.util.*;

public class CT루돌프의반란 {
    
    static class Santa {
        int r;
        int c;
        int score;
        int dir;
        int faintTurn;
        boolean faint;
        boolean fail;

        Santa(int r, int c, int score, boolean faint, boolean fail) {
            this.r = r;
            this.c = c;
            this.score = score;
            this.faint = faint;
            this.fail = fail;
        }
    }

    static class Rudolf {
        int r;
        int c;
        int dir;

        Rudolf(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Point implements Comparable<Point> {
        int r;
        int c;
        int dist;
        int dir;

        Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    
        Point(int r, int c, int dist, int dir) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.dir = dir;
        }

        @Override
        public int compareTo(Point o) {
            if(this.dist == o.dist) {
                if(this.r == o.r) return o.c - this.c;
                return o.r - this.r;
            }
            return this.dist - o.dist;
        }
    }
    static int n, m, p, c, d;
    static int[][] board;       // rudolf is 99
    static int numOfAliveSantas;
    static Rudolf rudolf;
    static Santa[] santas;
    static PriorityQueue<Point> queue = new PriorityQueue<>();
    static int[] dxR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dyR = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dxS = {0, 1, 0, -1};
    static int[] dyS = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        numOfAliveSantas = p;
        board = new int[n + 1][n + 1];
        santas = new Santa[p + 1];

        st = new StringTokenizer(br.readLine());
        int rudolfR = Integer.parseInt(st.nextToken());
        int rudolfC = Integer.parseInt(st.nextToken());
        board[rudolfR][rudolfC] = 99;
        rudolf = new Rudolf(rudolfR, rudolfC);

        for(int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = n;
            santas[n] = new Santa(r, c, 0, false, false);
        }

        while(m-- > 0) {
            moveRudolf();
            for(int i = 1; i <= p; i++) {
                if(santas[i].faint || santas[i].fail) continue;
                moveSanta(i);
            }

            for(int i = 1; i <= p; i++) {
                if(santas[i].fail) continue;
                if(santas[i].faint && m != santas[i].faintTurn) {
                    santas[i].faint = false;
                }
                santas[i].score++;
            }
        }
        endGame();
    }

    static void moveRudolf() {

        queue.clear();
        for(int i = 1; i <= p; i++) {
            if(santas[i].fail) continue;
            int dist = calculateDistance(rudolf.r, rudolf.c, santas[i].r, santas[i].c);
            queue.add(new Point(santas[i].r, santas[i].c, dist));
        }
        Point poll = queue.poll();

        queue.clear();
        for(int d = 0; d < 8; d++) {
            int xx = rudolf.r + dxR[d];
            int yy = rudolf.c + dyR[d];
            if(xx < 1 || xx > n || yy < 1 || yy > n) continue;
            int dist = calculateDistance(xx, yy, poll.r, poll.c);
            queue.add(new Point(xx, yy, dist, d));
        }
        poll = queue.poll();
        
        // reset position of rudolf
        board[rudolf.r][rudolf.c] = 0;
        rudolf.r = poll.r;
        rudolf.c = poll.c;

        // collide
        if(board[rudolf.r][rudolf.c] > 0) {
            collide(true, board[rudolf.r][rudolf.c], poll.dir);
        }
        board[rudolf.r][rudolf.c] = 99;
    }

    static void moveSanta(int idx) {
        
        queue.clear();

        for(int d = 0; d < 4; d++) {
            int xx = santas[idx].r + dxS[d];
            int yy = santas[idx].c + dyS[d];
            if(xx < 1 || xx > n || yy < 1 || yy > n) continue;
            if(0 < board[xx][yy] && board[xx][yy] < 99) continue;

            int dist = calculateDistance(xx, yy, rudolf.r, rudolf.c);
            queue.add(new Point(d, -1, dist, d));
        }
        if(queue.isEmpty()) return;

        Point poll = queue.poll();
        int currentDist = calculateDistance(santas[idx].r, santas[idx].c, rudolf.r, rudolf.c);
        if(currentDist <= poll.dist) return;

        // reset position of rudolf
        board[santas[idx].r][santas[idx].c] = 0;
        santas[idx].r += dxS[poll.dir];
        santas[idx].c += dyS[poll.dir];
       
        // collide
        if(board[santas[idx].r][santas[idx].c] == 99) {
            collide(false, idx, poll.dir);
        }
        else {
            board[santas[idx].r][santas[idx].c] = idx;
        }
    }

    static int calculateDistance(int r1, int c1, int r2, int c2) {
        return (int)(Math.pow(r1 - r2, 2) + Math.pow(c1 - c2, 2));
    }

    static void collide(boolean isRudolf, int idx, int dir) {

        if(isRudolf) {
            santas[idx].score += c;
            dir = isRudolf ? dir : (3 - dir) * 2;
            interact(idx, c, dir);
        }
        else {
            santas[idx].score += d;
            if(!isRudolf) {
                dir = dir < 2 ? (1 - dir) * 2 : (5 - dir) * 2;
            }
            interact(idx, d, dir);
        }

        santas[idx].faint = true;
        santas[idx].faintTurn = m;
    }

    static void interact(int idx, int amount, int dir) {
        
        int xx = santas[idx].r + dxR[dir] * amount;
        int yy = santas[idx].c + dyR[dir] * amount;

        if(xx < 1 || xx > n || yy < 1 || yy > n) {
            santas[idx].fail = true;
            if(--numOfAliveSantas == 0) endGame();
            return;
        }

        int target = board[xx][yy];
        board[xx][yy] = idx;
        santas[idx].r = xx;
        santas[idx].c = yy;
        if(target > 0) interact(target, 1, dir);
    }

    private static void endGame() {
        
        StringBuffer sb = new StringBuffer();
        for(int i = 1; i <= p; i++) {
            sb.append(santas[i].score + " ");
        }
        System.out.println(sb.toString());
        System.exit(0);
    }
}