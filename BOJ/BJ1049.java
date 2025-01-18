import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ1049 {

    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int ma = Integer.MAX_VALUE;
        int mb = Integer.MAX_VALUE;

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ma = Integer.min(ma, a);
            mb = Integer.min(mb, b);
        }

        int numOfPackage = n / 6;
        int numOfPackageRemain =  n % 6;

        int answer = Integer.min(n * mb, numOfPackage * ma + numOfPackageRemain * mb);
        answer = Integer.min(answer, (numOfPackage + 1) * ma);
        System.out.println(answer);
    }
}