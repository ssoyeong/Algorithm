import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 톱니바퀴

public class BJ14891 {

    static int k;
    static int num;
    static int dir;
    static int[][] wheels = new int[5][8];
    static int[] directionToRotate = new int[5];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 1; i <= 4; i++) {
            String line = br.readLine();
            for(int j = 0; j < 8; j++) {
                if(line.charAt(j) == '1') wheels[i][j] = 1;
            }
        }

        k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());

            Arrays.fill(directionToRotate, 0);
            rotateWheels();
        }

        int ans = calculateScore();
        System.out.println(ans);
    }

    private static void rotateWheels() {

        directionToRotate[num] = dir;
        
        // 타겟 왼쪽 회전
        if(num != 1 && wheels[num-1][2] != wheels[num][6]) {
            directionToRotate[num-1] = -dir;
        }
        // 타겟 오른쪽 회전
        if(num != 4 && wheels[num][2] != wheels[num+1][6]) {
            directionToRotate[num+1] = -dir;
        }

        /***************** 타겟 번호에 따라 회전 *****************/
        // 타겟이 1번일 경우
        if(num == 1 && directionToRotate[num+1] != 0) {
            // 3번째 회전
            rotateDoubleBackWheel();
            // 4번째 회전
            if(directionToRotate[num+2] != 0 && wheels[num+2][2] != wheels[num+3][6]) {
                directionToRotate[num+3] = -dir;
            }
        }

        // 타겟이 4번일 경우
        if(num == 4 && directionToRotate[num-1] != 0) {
            // 2번째 회전
            rotateDoubleFrontWheel();
            // 1번째 회전
            if(directionToRotate[num-2] != 0 && wheels[num-3][2] != wheels[num-2][6]) {
                directionToRotate[num-3] = -dir;
            }
        }

        // 타겟이 2번일 경우
        if(num == 2 && directionToRotate[num+1] != 0) {
            // 4번째 회전
            rotateDoubleBackWheel();
        }

        // 타겟이 3번일 경우
        if(num == 3 && directionToRotate[num-1] != 0) {
            // 1번째 회전
            rotateDoubleFrontWheel();
        }

        // directionToRotate[]에 담긴 방향대로 회전
        for(int i = 1; i <= 4; i++) {
            if(directionToRotate[i] == 1) {
                rotateClockwise(i);
            }
            else if(directionToRotate[i] == -1) {
                rotateCounterclockwise(i);
            }
        }
    }

    private static void rotateClockwise(int number) {
        
        int temp = wheels[number][7];
        for(int i = 7; i > 0; i--) {
            wheels[number][i] = wheels[number][i-1];
        }
        wheels[number][0] = temp;
    }

    private static void rotateCounterclockwise(int number) {
 
        int temp = wheels[number][0];
        for(int i = 0; i < 7; i++) {
            wheels[number][i] = wheels[number][i+1];
        }
        wheels[number][7] = temp;
    }

    private static void rotateDoubleFrontWheel() {
        if(wheels[num-2][2] != wheels[num-1][6]) {
            directionToRotate[num-2] = dir;
        }
    }

    private static void rotateDoubleBackWheel() {
        if(wheels[num+1][2] != wheels[num+2][6]) {
            directionToRotate[num+2] = dir;
        }
    }

    private static int calculateScore() {

        int total = 0;
        for(int i = 1; i <= 4; i++) {
            if(wheels[i][0] == 1) {
                total += Math.pow(2, i-1);
            }
        }

        return total;
    }
    
}
