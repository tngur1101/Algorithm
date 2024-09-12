import java.util.*;
import java.io.*;


public class Main {
	static class Player{
		int r;
		int c;
		int hp;
		int du;
		public Player(int r, int c, int hp, int du) {
			this.r = r;
			this.c = c;
			this.hp = hp;
			this.du = du;
		}
	}
	static int N,H,D;
	static int[][] map;
	static int[][] visited;	// 거리로 하려했는데 이상해서 체력이랑 내구도 최대인애로 교체
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Player player;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				if(s.charAt(j) == 'S') {
					map[i][j] = 1;
					player = new Player(i,j,H,0);
				} else if(s.charAt(j) == '.') {
					map[i][j] = 0;
				} else if(s.charAt(j) == 'U') {
					map[i][j] = 2;
				} else {
					map[i][j] = -1;
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Player> q = new LinkedList<>();
		visited[player.r][player.c] = player.hp;
		q.offer(player);
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
//			System.out.println("size: " + size);
			while(size-- > 0) {
				Player p = q.poll();
				
//				System.out.println("현재 위치");
//				System.out.println("r:  " + p.r + ", c : " + p.c);
				
				for(int d = 0; d < 4; d++) {
					int nr = p.r + dx[d];
					int nc = p.c + dy[d];
					
					if(!isInRange(nr, nc)) continue;
					
//					System.out.println("다음 위치");
//					System.out.println("nr:  " + nr + ", nc : " + nc);
					
					// 안전지대면 종료
					if(map[nr][nc] == -1) {
						return cnt+1;
					}
					
//					System.out.println("내구도 및 체력 처리 전");
//					System.out.println("p.du : " + p.du);
//					System.out.println("p.hp : " + p.hp);
					
					// 처음에 이렇게 변수 선언을 안하고 바로 p.du = D, p.du--로 했더니 문제가 발생
					// 값자체가 갱신이 되어버리면서 같은 상황에서 바라보는 경우의 수인데 값은 이미 갱신되어있는 상태
					int nextDuration = p.du;
					int nextHp = p.hp;
					
					// 새 우산이 있으면 갈아끼워주고
					if(map[nr][nc] == 2) {
						nextDuration = D;
					}
					
					// 우산 내구도가 있으면 먼저 줄여주고 없으면 hp 깎아주고
					// 아 여기도 조건문 안에 조건을 h.du > 0으로 하면 틀림
					// 그 이유는 업데이트를 nextDuration으로 해주기때문
					if(nextDuration > 0) nextDuration--;
					else nextHp--;
					
					// 만약 hp가 0이면 continue
					if(nextHp == 0) continue;
					
//					System.out.println("내구도 및 체력 처리 후");
//					System.out.println("p.du : " + p.du);
//					System.out.println("p.hp : " + p.hp);
//					
//					System.out.println("nextHp : " + nextHp);
//					System.out.println("nextDuration : " + nextDuration);
					
					// 그리고 만약 내가 길을 가는데 이 길까지 오는데 내 체력 + 우산내구도가 더 크면 갱신
					if(nextDuration + nextHp > visited[nr][nc]) {
						visited[nr][nc] = nextHp + nextDuration;
						q.offer(new Player(nr, nc, nextHp, nextDuration));
					}
				}
			}
			cnt++;
		}
		
		return -1;
	}
	
	private static boolean isInRange(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
}