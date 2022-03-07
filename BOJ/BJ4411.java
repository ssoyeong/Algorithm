package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

// 여행 경비 정산
// https://junho0956.tistory.com/275

public class BJ4411 {
	
	static int n;
	static BigDecimal[] cost;
	static BigDecimal total;
	static BigDecimal avg;
	static Double exchanged1;
	static Double exchanged2;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			cost = null;
			total = BigDecimal.ZERO;
			exchanged1 = 0.0;
			exchanged2 = 0.0;
			n = Integer.parseInt(br.readLine());
			
			if(n == 0) break;
			
			cost = new BigDecimal[n];
			for(int i = 0; i < n; i++) {
				
				cost[i] = new BigDecimal(br.readLine());
				total = total.add(cost[i]);
			}
			
			avg = total.divide(BigDecimal.valueOf(n));
			avg = avg.setScale(2, BigDecimal.ROUND_CEILING);
			
			Double sub;
			for(int i = 0; i < n; i++) {
				
				sub = avg.subtract(cost[i]).doubleValue();
				if(sub >= 0) {
					exchanged1 += sub;
				}
				else {
					exchanged2 += sub * -1;
				}
			}
			
			System.out.printf("$%.2f\n", Math.min(exchanged1, exchanged2));
		}
	}
}
