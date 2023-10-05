import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 티떱 숲 : R행 C열
 * 
 * 비어있는 곳 : .
 * 물이 차있는 곳 : *
 * 돌 : X
 * 비버 굴 : D
 * 고슴도치 위치 : S
 * 
 * 매 분 고슴도치는 인접한 네 칸 중 하나로 이동 가능
 * 매 분 물은 비어있는 칸으로 확장(인접한 네칸)
 * 물과 고슴도치는 돌 통과 x
 * 고슴도치는 물이 있는 곳 x, 물도 비버 집 확장 x
 * 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간 구하기
 * ** 고슴도치는 물이 찰 예정인 칸으로 이동 x
 * 
 * 1. 물을 먼저 확장한다.
 * 2. 물을 확장 이후에 고슴도치가 bfs를 이용하여 이동한다.
 * 3. 반복한다.
 * 
 * @author SSAFY
 *
 */

public class Main {
	//상,좌,하,우
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,-1,0,1};
	static int R,C;
	static char[][] map;
	static boolean[][] visited;	//방문 배열은 고슴도치의 움직임만 저장하도록 한다.
	
	static class Loc{
		int r;
		int c;
		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Loc BeaverHome;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		Loc Hedgehog = null;
		Queue<Loc> wq = new LinkedList<>();
		
		for(int i=0;i<R;i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				map[i][j] = line[j];
				if(map[i][j]=='D') {
					BeaverHome = new Loc(i,j);
				}
				if(map[i][j]=='S') {
					Hedgehog = new Loc(i,j);
				}
				if(map[i][j]=='*') {
					wq.offer(new Loc(i,j));
				}
			}
		}
		
//		print();
//		System.out.println("고슴도치 위치: "+Hedgehog.r+" "+Hedgehog.c);
		
		//고슴도치가 비버 집에 도착하면 끝?
		int result = bfs(Hedgehog, wq);
		
//		print();
		if(result==0) {
			System.out.println("KAKTUS");
		}
		else {
			System.out.println(result);
		}
		
	}
	
	private static int bfs(Loc loc, Queue<Loc> wq) {	// 고슴도치 혹은 물의 정보를 받아온다.
		int cnt = 0;
		Queue<Loc> q = new LinkedList<>();
		visited[loc.r][loc.c] = true;
		q.offer(loc);
		
		while(!q.isEmpty()) {
				int wsize = wq.size();
				while(wsize-->0) {
					Loc wcur = wq.poll();
					
					for(int d=0;d<4;d++) {
						int wnr = wcur.r+dr[d];
						int wnc = wcur.c+dc[d];
						
						if(isInRange(wnr, wnc) && map[wnr][wnc]=='.') {
							map[wnr][wnc]='*';
							wq.offer(new Loc(wnr, wnc));
						}
					}
				}
				
//				System.out.println("bfs 물 돌고나서");
//				print();
				int size = q.size();
				while(size-->0) {
					Loc cur = q.poll();
					
				for(int d=0;d<4;d++) {
					int nr = cur.r+dr[d];
					int nc = cur.c+dc[d];
					if(nr==BeaverHome.r && nc == BeaverHome.c) return ++cnt;
					
					if(isInRange(nr, nc) && map[nr][nc]=='.' && !visited[nr][nc]) {
						visited[nr][nc]=true;
						map[nr][nc]='S';
						q.offer(new Loc(nr,nc));
					}
				}
//				System.out.println("bfs 고슴도치 돌고나서");
//				print();
//				System.out.println("cnt: "+cnt);
			}
			cnt++;
		}
		
		
		return 0;
	}
	
	private static boolean isInRange(int r, int c) {
		return r>=0 && c>=0 && r<R && c<C;
	}
	
	private static void print() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}