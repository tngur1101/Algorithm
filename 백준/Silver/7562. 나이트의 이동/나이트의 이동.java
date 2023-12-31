import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Loc{
		int r;
		int c;
		int cnt;
		public Loc(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int[][] chessBoard;
	static boolean[][] visited;
	static final int[] dr = {-2, -2, -1, 1, 2, 2, 1, -1};
	static final int[] dc = {-1, 1, 2, 2, 1, -1, -2, -2};
	static int l;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// testCase의 개수
		int t = Integer.parseInt(br.readLine());

		for(int i=0;i<t;i++) {
			l = Integer.parseInt(br.readLine());
			
			chessBoard = new int[l][l];
			visited = new boolean[l][l];
			
			st = new StringTokenizer(br.readLine());
			int s_r = Integer.parseInt(st.nextToken());
			int s_c = Integer.parseInt(st.nextToken());
			Loc start = new Loc(s_r, s_c, 0);
			
			st = new StringTokenizer(br.readLine());
			int e_r = Integer.parseInt(st.nextToken());
			int e_c = Integer.parseInt(st.nextToken());
			Loc end = new Loc(e_r, e_c, 0);
			
			int answer = Integer.MAX_VALUE;
			answer = Math.min(answer, bfs(start, end));
			sb.append(answer).append(System.lineSeparator());
		}
		System.out.println(sb);
		
	}
	
	private static int bfs(Loc start, Loc end) {
		Queue<Loc> queue = new LinkedList<>();
		queue.offer(start);
		visited[start.r][start.c] = true;
		
		while(!queue.isEmpty()) {
			Loc cur = queue.poll();
			
			if(cur.r == end.r && cur.c==end.c) return cur.cnt;
			for(int d=0;d<8;d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				if(isInRange(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Loc(nr, nc, cur.cnt+1));
				}
			}
		}
		return 0;
	}
	
	private static boolean isInRange(int r, int c) {
		return r>=0 && c>=0 && r<l && c<l;
	}
}