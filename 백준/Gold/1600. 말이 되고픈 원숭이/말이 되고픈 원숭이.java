import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 4방 탐색과 나이트의 움직임을 섞어야 함
 * 문제에서 최소한의 동작을 요구하기 때문에 bfs를 사용
 * 
 * 말처럼 뛰는 것은 장애물을 건널 수 있음
 * 
 * 1. 말처럼 갈 수 있는 횟수가 K번으로 정해져있기 때문에
 * 2. 사방탐색을 하며 이동하다 장애물을 만나면 말처럼 뛴다.
 * 3. K>0이고 장애물이 주변에 있고 주변에 0이 있으면 사방탐색으로 이동하고
 * 4. K>0인데 주변에 0이 없고 장애물만 있거나 visited면 말처럼 뛴다.
 * 문제 : 이 방법은 최솟값을 가져오지 못할 수 있다. 예) K가 많고 장애물이 별로 없을 때
 * 
 * @author SSAFY
 *
 */


public class Main {
	
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static final int[] hDr = {-2,-1,1,2,2,1,-1,-2};
	static final int[] hDc = {1,2,2,1,-1,-2,-2,-1};
	static int K,W,H;
	static int[][] map;
	static int[][][] visited;
	static class Node{
		int r;	//행
		int c;	//열
		int k;	//k사용횟수
		public Node(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());	//가로길이
		H = Integer.parseInt(st.nextToken());	//세로길이
		
		map = new int[H][W];
		visited = new int[H][W][K+1];
		
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
		
	}
	
	public static int bfs() {
		Queue<Node> q = new LinkedList<>();
		
		visited[0][0][0]=0;
		q.offer(new Node(0,0,0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			//목적지 도착했을 경우 해당 값 리턴
			if(cur.r==H-1 && cur.c==W-1) return visited[cur.r][cur.c][cur.k]; 
			
			//목적지가 아닐 경우 사방탐색
			for(int d=0;d<4;d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				//밖으로 나갈때
				if(!isInRange(nr, nc)) continue;
				//이미 방문한 곳일 떄
				if(visited[nr][nc][cur.k]!=0) continue;
				
				//이동가능한 곳이면
				if(map[nr][nc]==0) {
					visited[nr][nc][cur.k]=visited[cur.r][cur.c][cur.k]+1;
					q.offer(new Node(nr, nc, cur.k));
				}
			}
			
			if(cur.k<K) {
				for(int d=0;d<8;d++) {
					int nr = cur.r+hDr[d];
					int nc = cur.c+hDc[d];
					
					if(!isInRange(nr,nc))continue;
					if(visited[nr][nc][cur.k+1]!=0) continue;
					
					if(map[nr][nc]==0) {
						visited[nr][nc][cur.k+1]=visited[cur.r][cur.c][cur.k]+1;
						q.offer(new Node(nr,nc,cur.k+1));
					}
				}
			}
			
		}
		
		
		return -1;
		
	}
	
	public static boolean isInRange(int x, int y) {
		return x>=0 && y>=0 && x<H && y<W;
	}
	
	public static void print() {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}