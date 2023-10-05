import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동
 * 한번에 상하좌우 인접한 곳으로 1칸씩 이동
 * 링크가 잃을 수 밖에 없는 최소 금액은?
 * 
 * 간단한 DFS,BFS 문제? => 다익스트라를 활용하는 문제
 * (0,0)에서 (N-1,N-1)로 가는 모든 경우의 손실 금액을 계산하고 min값을 도출하면 된다고 생각한다.
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,-1,0,1};
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int w;
		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = 0;
		int cnt = 0;	//실행 횟수
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			int answer = 0;
			//종료
			if(N==0) {
				break;
			}
			cnt++;
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			
			answer = bfs(new Node(0,0,map[0][0]));
			
			
			sb.append("Problem").append(" ").append(cnt).append(": ").append(answer).append(System.lineSeparator());
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(Node node) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited[node.r][node.c] = true;
		pq.offer(node);
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.r==N-1 && cur.c==N-1) return cur.w;
			
			for(int d=0;d<4;d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				if(isInRange(nr, nc) && !visited[nr][nc]) {
					pq.offer(new Node(nr,nc,cur.w+map[nr][nc]));
					visited[nr][nc]=true;
				}	
			}
		}
		
		return 0;
	}
	
	private static boolean isInRange(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	private static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}