package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 보석 도둑

class Jewel implements Comparable<Jewel> {
	
	int weight;
	int price;
	
	Jewel(int weight, int price){
		
		this.weight = weight;
		this.price = price;
	}
	
	@Override
	public int compareTo(Jewel j) {
		
		if(this.weight == j.weight) return j.price - this.price;
		return this.weight - j.weight;
	}
}

public class BJ1202 {
	
	static int n;
	static int k;
	static Jewel[] jewelList;
	static Integer[] bagList;
	static long price;		// 가방 300,000개가 보석 가격 1,000,000을 담으면 int 범위 초과하므로 long 사용
	static PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.reverseOrder());
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		jewelList = new Jewel[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			jewelList[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		bagList = new Integer[k];
		for(int i = 0; i < k; i++) {
			bagList[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(bagList);
		Arrays.sort(jewelList);

		int bpos = 0;
		int jpos = 0;
		while(bpos < k) {
			while(jpos < n && jewelList[jpos].weight <= bagList[bpos]) {
				queue.add(jewelList[jpos].price);
				jpos++;
			}
			
			if(!queue.isEmpty()) {
				price += queue.poll();
			}
			bpos++;
		}
		
		System.out.println(price);
		
	}

}
