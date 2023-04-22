import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

// 집합

public class BJ11723 {
    
    static int m;
    static HashSet<Integer> set = new HashSet<>();
    static HashSet<Integer> allCommandSet = new HashSet<>();

    public static void main(String[] args) throws IOException {

        // all 명령 시 변경할 집합 초기화
        for(int i = 1; i <= 20; i++) {
            allCommandSet.add(i);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("all")) {
                all();
            }
            else if(cmd.equals("empty")) {
                set.clear();
            }
            else {
                int x = Integer.parseInt(st.nextToken());

                if(cmd.startsWith("a")) {
                    set.add(x);
                }
                else if(cmd.startsWith("r")) {
                    set.remove(x);
                }
                else if(cmd.startsWith("t")) {
                    toggle(x);
                }
                else {
                    int result = check(x);
                    sb.append(result).append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void all() {

        set.clear();
        set.addAll(allCommandSet);   
    }

    private static void toggle(int x) {

        if(set.contains(x)) set.remove(x);
        else set.add(x);
    }

    private static int check(int x) {

        if(set.contains(x)) return 1;
        else return 0;
    }
}
