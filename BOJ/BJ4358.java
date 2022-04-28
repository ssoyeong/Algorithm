package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

// 생태학

public class BJ4358 {
	
	static TreeMap<String, Integer> trees = new TreeMap<>();
	static double total;
	static String input;
	
	public static void main(String[] agrs) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true) {
			input = br.readLine();
			if(input == null) break;
			total++;
			if(!trees.containsKey(input)) {
				trees.put(input, 1);
			}
			else {
				int cnt = trees.get(input);
				trees.remove(input);
				trees.put(input, ++cnt);
			}
		}
		
		for(Entry<String, Integer> entry : trees.entrySet()) {
			double percent = entry.getValue() / total * 100;
			sb.append(entry.getKey()).append(" ").append(String.format("%.4f", percent)).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
