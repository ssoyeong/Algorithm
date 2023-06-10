import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 2048 (Easy)

public class BJ12100 {

    static int n;
    static int max;
    static int[] cmd = new int[5];
    static int[][] board;
    static int[][] temp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        temp = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0);
        System.out.println(max);
    }

    private static void backTracking(int depth) {

        if(depth == 5) {
            moveBlocks();
            searchMaxBlock();
            return;
        }

        for(int i = 0; i < 4; i++) {
            cmd[depth] = i;
            backTracking(depth + 1);
        }
    }

    private static void moveBlocks() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                temp[i][j] = board[i][j];
            }
        }

        // cmd에 담긴 방향 순서대로 블록을 움직임
        for(int i = 0; i < 5; i++) {
            if(cmd[i] == 0) {
                upside();
            }
            else if(cmd[i] == 1) {
                leftside();
            }
            else if(cmd[i] == 2) {
                downside();
            }
            else {
                rightside();
            }
        }
    }

    private static void upside() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int target = temp[j][i];

                /* target이 비었다면 해당 줄에서 target과 가장 가까운 블록을 찾아 target 자리에 넣어줌 */
                if(target == 0) {

                    boolean flag = false;                   // 해당 줄에 블록이 있는지 판단
                    for(int k = j + 1; k < n; k++) {
                        if(temp[k][i] != 0) {
                            temp[j][i] = temp[k][i];
                            temp[k][i] = 0;
                            flag = true;
                            break;
                        }
                    }

                    if(!flag) break;                        // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
                }

                /* target 자리에 들어갈 수를 찾음 */
                target = temp[j][i];
                boolean flag = false;                       // 해당 줄에 블록이 있는지 판단

                for(int k = j + 1; k < n; k++) {
                    if(temp[k][i] != 0) {
                        if(target == temp[k][i]) {         // target의 수와 가장 가까운 블록의 수가 서로 같다면
                            temp[j][i] *= 2;
                            temp[k][i] = 0;
                        }
                        flag = true;
                        break;
                    }
                }

                if(!flag) break;                            // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
            }
        }
    }

    private static void leftside() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int target = temp[i][j];

                /* target이 비었다면 해당 줄에서 target과 가장 가까운 블록을 찾아 target 자리에 넣어줌 */
                if(target == 0) {

                    boolean flag = false;                   // 해당 줄에 블록이 있는지 판단
                    for(int k = j + 1; k < n; k++) {
                        if(temp[i][k] != 0) {
                            temp[i][j] = temp[i][k];
                            temp[i][k] = 0;
                            flag = true;
                            break;
                        }
                    }

                    if(!flag) break;                        // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
                }

                /* target 자리에 들어갈 수를 찾음 */
                target = temp[i][j];
                boolean flag = false;                       // 해당 줄에 블록이 있는지 판단

                for(int k = j + 1; k < n; k++) {
                    if(temp[i][k] != 0) {
                        if(target == temp[i][k]) {         // target의 수와 가장 가까운 블록의 수가 서로 같다면
                            temp[i][j] *= 2;
                            temp[i][k] = 0;
                        }
                        flag = true;
                        break;
                    }
                }

                if(!flag) break;                            // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
            }
        }
    }

    private static void downside() {

        for(int i = 0; i < n; i++) {
            for(int j = n - 1; j >= 0; j--) {
                int target = temp[j][i];

                /* target이 비었다면 해당 줄에서 target과 가장 가까운 블록을 찾아 target 자리에 넣어줌 */
                if(target == 0) {

                    boolean flag = false;                   // 해당 줄에 블록이 있는지 판단
                    for(int k = j - 1; k >= 0; k--) {
                        if(temp[k][i] != 0) {
                            temp[j][i] = temp[k][i];
                            temp[k][i] = 0;
                            flag = true;
                            break;
                        }
                    }

                    if(!flag) break;                        // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
                }

                /* target 자리에 들어갈 수를 찾음 */
                target = temp[j][i];
                boolean flag = false;                       // 해당 줄에 블록이 있는지 판단

                for(int k = j - 1; k >= 0; k--) {
                    if(temp[k][i] != 0) {
                        if(target == temp[k][i]) {         // target의 수와 가장 가까운 블록의 수가 서로 같다면
                            temp[j][i] *= 2;
                            temp[k][i] = 0;
                        }
                        flag = true;
                        break;
                    }
                }

                if(!flag) break;                            // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
            }
        }
    }

    private static void rightside() {

        for(int i = 0; i < n; i++) {
            for(int j = n - 1; j >= 0; j--) {
                int target = temp[i][j];

                /* target이 비었다면 해당 줄에서 target과 가장 가까운 블록을 찾아 target 자리에 넣어줌 */
                if(target == 0) {

                    boolean flag = false;                   // 해당 줄에 블록이 있는지 판단
                    for(int k = j - 1; k >= 0; k--) {
                        if(temp[i][k] != 0) {
                            temp[i][j] = temp[i][k];
                            temp[i][k] = 0;
                            flag = true;
                            break;
                        }
                    }

                    if(!flag) break;                        // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
                }

                /* target 자리에 들어갈 수를 찾음 */
                target = temp[i][j];
                boolean flag = false;                       // 해당 줄에 블록이 있는지 판단

                for(int k = j - 1; k >= 0; k--) {
                    if(temp[i][k] != 0) {
                        if(target == temp[i][k]) {         // target의 수와 가장 가까운 블록의 수가 서로 같다면
                            temp[i][j] *= 2;
                            temp[i][k] = 0;
                        }
                        flag = true;
                        break;
                    }
                }

                if(!flag) break;                            // 해당 줄에 블록이 없으므로 다음 줄로 넘어감
            }
        }
    }

    private static void searchMaxBlock() {

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                max = Integer.max(max, temp[i][j]);
            }
        }
    }
}
