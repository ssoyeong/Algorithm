import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 드래곤 앤 던전

public class BJ16434 {

    private static class Room {
        int info;
        int atk;
        int hp;

        Room(int info, int atk, int hp) {
            this.info = info;
            this.atk = atk;
            this.hp = hp;
        }
    }
    static int n;
    static long ans;
    static long atk, curHp;
    static Room[] rooms;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        rooms = new Room[n];
        atk = Long.parseLong(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            rooms[i] = new Room(t, a, h);
            
            if(t == 2) atk += a;
        }

        solution();
        System.out.println(ans);
    }

    private static void solution() {

        for(int i = n-1; i >= 0; i--) {

            Room target = rooms[i];

            if(target.info == 1) {

                long time = target.hp / atk;
                if(target.hp % atk == 0) time--;

                long minHp = time * target.atk;
                curHp += minHp;
                ans = Long.max(ans, curHp + 1);
            }
            else {
                atk -= target.atk;
                curHp -= target.hp;
                if(curHp < 0) curHp = 0;
            }
        }
    }
    
}
