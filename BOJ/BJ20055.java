import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

// 컨베이어 벨트 위의 로봇

public class BJ20055 {

    static int n, k;
    static int cnt;
    static int[] value;
    static boolean[] isRobot;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        value = new int[n * 2 + 1];
        isRobot = new boolean[n * 2 + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n * 2; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        int step = 1;
        while(true) {
            // 1단계. 벨트와 로봇이 한 칸 이동
            rotateBelt();
            // 2단계. 로봇이 한 칸 이동
            moveRobots();
            // 3단계. 로봇을 올림
            putRobot();
            // 4단계. 종료 조건
            if(cnt >= k) break;

            step++;
        }

        System.out.println(step);
    }

    private static void rotateBelt() {

        value[0] = value[n * 2];
        isRobot[0] = isRobot[n * 2];

        for(int i = n * 2; i > 0; i--){
            value[i] = value[i - 1];
            isRobot[i] = isRobot[i - 1];

            // 로봇이 내리는 위치에 도달한 경우 즉시 내림
            if(isRobot[n]) {
                isRobot[n] = false;
            }
        }
    }

    private static void moveRobots() {

        if(isRobot[n * 2]) isRobot[0] = true;

        for(int i = n * 2 - 1; i >= 0; i--) {
            if(isRobot[i] && !isRobot[i + 1] && value[i + 1] >= 1) {
                isRobot[i] = false;
                isRobot[i + 1] = true;
                value[i + 1]--;
                if(value[i + 1] == 0) cnt++;

                // 로봇이 내리는 위치에 도달한 경우 즉시 내림
                if((i + 1) == n) {
                    isRobot[i + 1] = false;
                }
            }
        }
    }

    private static void putRobot() {

        if(value[1] != 0) {
            isRobot[1] = true;
            value[1]--;
            if(value[1] == 0) cnt++;
        }
    }

}
