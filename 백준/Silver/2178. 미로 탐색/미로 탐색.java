import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로 : N x M
 * 미로에서 1 : 이동 가능한 칸
 * 미로에서 0 : 이동 불가능한 칸
 * 
 * (1,1)에서 출발 (N,M) 목적지
 * (1,1)에서 (N,M)까지 가는 경우 중 최소의 칸 수
 * 
 * 각 수는 붙어서 입력 받음
 * 
 * @author SSAFY
 *
 */


public class Main {
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,-1,0,1};
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
		Node start = new Node(0,0);
		Node end = new Node(N-1,M-1);
//		print();
		int answer = bfs(start, end);
		System.out.println(answer);
	}
	
	static int bfs(Node start,Node end) {
		int cnt=1;
		Queue<Node> queue = new LinkedList<>();
		queue.offer(start);
		visited[start.r][start.c]=true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				Node cur = queue.poll();
				
				if(cur.r==end.r && cur.c==end.c) return cnt;
				for(int d=0;d<4;d++) {
					int nr = cur.r+dr[d];
					int nc = cur.c+dc[d];
					
					if(isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc]==1) {
						visited[nr][nc] = true;
						queue.offer(new Node(nr, nc));
					}
				}
			}
			cnt++;
		}
		
		
		return cnt;
	}
	
	static boolean isInRange(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}