import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 상어 중학교

public class BJ21609 {

	private static class Group implements Comparable<Group>{
		int id;
		int cnt;
		int cntOfRainbow;
		int r;		// 기준 블록의 행
		int c;		// 기준 블록의 열
		ArrayList<Point> blocks = new ArrayList<>();

		Group(int id, int r, int c) {
			this.id = id;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Group o) {
			if(this.cnt == o.cnt) {
				if(this.cntOfRainbow == o.cntOfRainbow) {
					if(this.r == o.r) {
						return o.c - this.c;
					}
					return o.r - this.r;
				}
				return o.cntOfRainbow - this.cntOfRainbow;
			}
			return o.cnt - this.cnt;
		}
	}
	private static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n, m;
	static int score;
	static int[][] board;		// 9인 경우, 제거된 블록
	static boolean[][] visited;
	static PriorityQueue<Group> groups = new PriorityQueue<>();		// 블록 그룹들을 담아 타겟 블록 그룹을 찾을 때 사용되는 큐
	static Queue<Point> queue = new LinkedList<>();					// bfs 탐색에 사용되는 큐
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new int[n][n];
		visited = new boolean[n][n];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solution();
		System.out.println(score);

	}
	
	private static void solution() {

		while(true) {

			// 타겟 블록 그룹을 찾음
			Group targetGroup = searchMaxGroup();
			// 블록 그룹이 존재하지 않는 경우, 플레이 종료
			if(targetGroup.id == -1) break;

			// 찾은 블록 그룹의 모든 블록을 제거
			deleteBlocks(targetGroup);

			// 격자에 중력이 작용
			doGravity();

			// 격자가 회전
			rotateBoard();

			// 격자에 중력이 작용
			doGravity();
			
			// 방문 여부 초기화
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					visited[i][j] = false;
				}
			}
		}
	}
	
	private static Group searchMaxGroup() {

		groups.clear();
		int id = 1;					// 블록 그룹에 매겨질 아이디
		boolean flag = false;		// 블록의 개수가 2 이상으로 이루어진 블록 그룹이 있는지의 여부

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j] || board[i][j] == -1 || board[i][j] == 0 || board[i][j] == 9) continue;

				Group newGroup = bfs(id, i, j);		// 기준 블록(board[i][j])에서부터 id값으로 블록 그룹을 찾음
				if(newGroup.cnt > 1) {
					flag = true;
					groups.add(newGroup);
				}
				
				id++;
			}
		}
		
		if(groups.size() == 0 || !flag) return new Group(-1, -1, -1);		// 블록 그룹이 만들어지지 않거나, 블록의 개수가 2 이상으로 이루어진 블록 그룹이 없는 경우
		return groups.poll();
	}

	private static Group bfs(int id, int r, int c) {

		Group group = new Group(id, r, c);		// 리턴할 블록 그룹 선언
		int targetBlock = board[r][c];
		int cnt = 1;
		int cntOfRainbow = 0;
		ArrayList<Point> tempBlocks = new ArrayList<>();		// 해당 블록 그룹에 담길 블록들을 임시 저장

		visited[r][c] = true;
		queue.add(new Point(r, c));
		tempBlocks.add(new Point(r, c));

		while(!queue.isEmpty()) {
			Point poll = queue.poll();

			for(int i = 0; i < 4; i++) {
				int xx = poll.x + dx[i];
				int yy = poll.y + dy[i];

				if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
				if(visited[xx][yy]) continue;

				if(board[xx][yy] == targetBlock || board[xx][yy] == 0) {

					cnt++;
					if(board[xx][yy] == 0) cntOfRainbow++;
					visited[xx][yy] = true;
					queue.add(new Point(xx, yy));
					tempBlocks.add(new Point(xx, yy));
				}
			}
		}
		
		group.cnt = cnt;
		group.cntOfRainbow = cntOfRainbow;
		group.blocks = tempBlocks;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] == 0) visited[i][j] = false;		// 무지개 블록은 다른 블록 그룹에서 재방문할 수 있으므로 false로 변경
			}
		}

		return group;
	}

	private static void deleteBlocks(Group targetGroup) {

		for(Point block : targetGroup.blocks) {		// 타겟 블록 그룹의 블록들을 제거
			board[block.x][block.y] = 9;
		}

		score += targetGroup.blocks.size() * targetGroup.blocks.size();
	}

	private static void doGravity() {

		for(int j = 0; j < n; j++) {
			for(int i = n - 1; i >= 0; i--) {
				for(int k = i; k < n - 1; k++) {
					
					if(board[k][j] == -1) continue;
					if(board[k][j] != 9 && board[k + 1][j] == 9) {		// 빈칸과 일반 블록을 버블 정렬하듯 자리를 바꾸어 빈칸을 위로 올리기
						int temp = board[k][j];
						board[k][j] = board[k + 1][j];
						board[k + 1][j] = temp;
					}
				}
			}
		}
	}
	
	private static void rotateBoard() {
		
		int[][] temp = new int[n][n];
		
		for(int i = n - 1; i >= 0; i--) {
			for(int j = 0; j < n; j++) {
				temp[n - i - 1][j] = board[j][i];
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j] = temp[i][j];
			}
		}
	}
	
}