import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 파이어볼이랑 토네이도도 힘들었는데
 * 얘네를 조합해서 파이어 스톰을 쓰는 미친 상어
 * 
 * 격자 : 2^n x 2^n
 * 
 * A[r][c]: (r,c)의 얼음의 양
 * 0인 경우 얼음 x
 * 
 * 1. 격자를 2^L x 2^L로 나눔
 * 2. 모든 부분 격자를 시계방향 90도로 회전
 * 3. 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄음
 * 인접: 상하좌우로 인접한 칸
 * 
 * 목표
 * 1.남아있는 얼음의 합
 * 2.남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
 * 
 * 어떻게 보면 종이접기랑 조금 유사할지도?
 */

class Ice{
	int r;
	int c;
	public Ice(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {

	static int N,Q;
	static int n;	//2의 N승
	static int[][] map;
	static int[] firestorm;
	
	//상, 좌, 하, 우
	static final int[] dx = {-1,0,1,0};
	static final int[] dy = {0,-1,0,1};
	
	static Queue<Ice> queue;
	static boolean[][]visited;
	
	static int totalIce;	//얼음의 총 개수
	static int maxIceLand;	//이어져있는 얼음의 가장 넓은 영역
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		n = (int) Math.pow(2, N);	
		map = new int[n][n];	//맵 초기화
		totalIce = 0;
		maxIceLand = 0;
		
		//얼음값 넣어주기
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		firestorm = new int[Q];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<Q;i++) {
			firestorm[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<Q;i++) {	//Q횟수 만큼
			//배열을 나눠서
			//돌리고
			//얼음을 녹일거임
			divideIce(firestorm[i]);
			melt();
		}
		
		//이걸 끝내고 나서
		//countIce를 이용해서 얼음의 총 개수 구하고
		countIce();
		sb.append(totalIce).append(System.lineSeparator());
		//bfs를 이용해서 가장 큰 얼음을 찾아야함
		bfs();
		sb.append(maxIceLand);
		System.out.println(sb);
	}
	
	//일단 for문을 도는데 2^l만큼의 크기만큼 뛰면서 돌아야함
	//그렇게 만들어진 모든 부분격자들이 모두 돌아야함
	private static void divideIce(int L) {
		//그러려면 이 부분격자 만들어주는 함수에서 계속 rotate를 해줘야할 듯?
		int[][] temp = new int[n][n];
		int l = (int)Math.pow(2, L);
		
		for(int i=0;i<n;i=i+l) {
			for(int j=0;j<n;j=j+l) {
				rotate(i,j,l,temp);
			}
		}
		
		map = temp;
	}
	
	
	//원래 90도 오른쪽 회전 식
	//temp[i][j] = map[n-1-j][i];
	private static void rotate(int r, int c,int l, int[][] temp) {
		for(int i=0;i<l;i++) {
			for(int j=0;j<l;j++) {
				temp[r+i][c+j] = map[r+l-1-j][c+i];
			}
		}

	}
	
	//해당 함수에서 temp를 쓴 이유
	//원래는 map함수로 바로바로 넣어줘서 했더니
	//얼음이 한번에 녹아야하는데, 바로바로 업데이트가 되다 보니까
	//값이 이상해짐.... 그래서 temp에 업데이트 값을 넣어주고 map은 변하지 않게 해서 한번에 처리함
	private static void melt() {
		//배열 복사
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			temp[i] = Arrays.copyOf(map[i], n);
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int cnt=0;	//얘는 주변 얼음 개수 세주는 애임 => 3개미만이면 해당 map의 값을 1줄일거임  //매번 세줘야해서 for문 안에서 초기화 해야함 => 이거땜에 개빡침
				if(map[i][j]==0)continue;	//얼음 없으면 패스
				for(int d=0;d<4;d++) {
					int nr = i+dx[d];
					int nc = j+dy[d];
					
					if(isInRange(nr, nc)) {
						if(map[nr][nc]>0)cnt++;	//얼음이 있기 때문에 주변 얼음 갯수 증가
					}
				}
				//4방향 모두 돌았으니
				//얼음의 개수 체크
				if(cnt<3)temp[i][j]--;
				
			}
		}
		
		map = temp;
	}
	
	//얼음 세주는 함수
	private static void countIce() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				totalIce += map[i][j];
			}
		}
	}
	
	private static void bfs() {
		queue = new LinkedList<>();
		visited = new boolean[n][n];
		
		//얼음이 없는 곳은 0 => map의 값을 다 더해주면 총 ice
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]>0 && !visited[i][j]) {
					queue.offer(new Ice(i,j));
					visited[i][j] = true;
					int cnt=1;
					
					while(!queue.isEmpty()) {
						Ice ice = queue.poll();
						
						for(int d=0;d<4;d++) {
							int nr = ice.r+dx[d];
							int nc = ice.c+dy[d];
							if(isInRange(nr, nc)) {
								if(map[nr][nc]>0 && !visited[nr][nc]) {
									visited[nr][nc]=true;
									queue.offer(new Ice(nr,nc));
									cnt++;
								}
							}
						}
					}
					
					maxIceLand = Math.max(maxIceLand, cnt);
				}
			}
		}

	}
	
	private static boolean isInRange(int x, int y) {
		return x>=0 && y>=0 && x<n && y<n;
	}

}