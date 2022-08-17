import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 낚시왕

public class BJ17143 {

    private static class Shark {

        int r;
        int c;
        int s;      // 속력
        int d;      // 이동 방향
        int z;      // 크기

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Shark(int z) {
            this.z = z;
        }

        @Override
        public boolean equals(Object obj) {
            return (this.z == ((Shark)obj).z) || ((this.r == ((Shark)obj).r) && (this.c == ((Shark)obj).c));
        }
    }

    static int R, C;
    static int m;
    static int answer;
    static Shark[][] map;
    static ArrayList<Shark> sharks = new ArrayList<>();
    static ArrayList<Shark> sharksTemp = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new Shark[R+1][C+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Shark input = new Shark(r, c, s, d, z);
            sharks.add(input);
            map[r][c] = input;
        }
        
        if(m != 0) solution();
        System.out.println(answer);
    }

    private static void solution() {

        for(int king = 1; king <= C; king++) {
            
            catchShark(king);

            sharksTemp.clear();
            for(int i = 0; i < sharks.size(); i++) {
                moveShark(sharks.get(i), sharks.get(i).s);
            }

            for(int i = 1; i <= R; i++) {
                Arrays.fill(map[i], null);
            }

            sharks.clear();
            for(int i = 0; i < sharksTemp.size(); i++) {

                if(map[sharksTemp.get(i).r][sharksTemp.get(i).c] == null) {
                    map[sharksTemp.get(i).r][sharksTemp.get(i).c] = sharksTemp.get(i);
                    sharks.add(sharksTemp.get(i));
                }
                else {
                    if(sharksTemp.get(i).z > map[sharksTemp.get(i).r][sharksTemp.get(i).c].z) {
                        map[sharksTemp.get(i).r][sharksTemp.get(i).c] = sharksTemp.get(i);
                        sharks.remove(new Shark(sharksTemp.get(i).r, sharksTemp.get(i).c));
                        sharks.add(sharksTemp.get(i));
                    }
                }
            }
        }
    }

    private static void catchShark(int king) {

        for(int i = 1; i <= R; i++) {

            if(map[i][king] != null) {
                answer += map[i][king].z;

                sharks.remove(new Shark(map[i][king].z));
                map[i][king] = null;
                return;
            }
        }
    }

    private static void moveShark(Shark shk, int remainDistance) {

        if(remainDistance == 0) {
            sharksTemp.add(shk);
        }
        else {

            int pd = possibleDistance(shk);

            if(remainDistance < pd) {
                
                if(shk.d == 1) shk.r -= remainDistance;
                else if(shk.d == 2) shk.r += remainDistance;
                else if(shk.d == 3) shk.c += remainDistance;
                else shk.c -= remainDistance;

                sharksTemp.add(shk);
                return;      
            }
            else {
                if(shk.d == 1) {
                    shk.r = 1;
                    shk.d = 2;
                }
                else if(shk.d == 2) {
                    shk.r = R;
                    shk.d = 1;
                }
                else if(shk.d == 3) {
                    shk.c = C;
                    shk.d = 4;
                }
                else {
                    shk.c = 1;
                    shk.d = 3;
                }

                moveShark(new Shark(shk.r, shk.c, shk.s, shk.d, shk.z), remainDistance - pd);
            }
        }
    }

    private static int possibleDistance(Shark shk) {

        if(shk.d == 1) return shk.r - 1;
        else if(shk.d == 2) return R - shk.r;
        else if(shk.d == 3) return C - shk.c;
        else return shk.c - 1;
    }
    
}
