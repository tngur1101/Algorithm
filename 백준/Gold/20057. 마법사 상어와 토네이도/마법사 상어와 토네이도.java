import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 토네이도는 격자 가운데부터 시작
 */

public class Main {
	
	static int N;
	static int map[][];
	
	//상,하,좌,우
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1};
	
	//나는 지금 현재 방향을 상:0 하:1 좌:2 우:3으로 설정했기 때문에
	static int nextDir[] = { 2, 3, 1, 0 }; // 현재 방향에서 다음방향
	static int sandout;	//격자 밖으로 나간 모래 양
	
	
	static int dsx[][] = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 },
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } }; // 모래 퍼지는 x방향
	static int dsy[][] = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } }; // 모래 퍼지는 y방향
	static int rate[] = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		tornado();
		System.out.println(sandout);
	}
	
	private static void tornado() {
		int cr = N / 2;	// 시작행
		int cc = N / 2; // 시작열
		int curdir = 2; // 시작 방향은 좌측(2)
		int nr = 0;	// 다음 칸 행
		int nc = 0; // 다음 칸 열
		int d = 1; // 이동해야 하는 칸 수
		int cnt = 0; // 이동 횟수
		int check = 0; // 이동해야 하는 칸만큼 이동을 2번 했는지

		while (true) {
			if (cr == 0 && cc == 0) { // (1,1) 도착하면 소멸됨
				break;
			}
			//다음 좌표 확인하면서
			nr = cr + dx[curdir];	
			nc = cc + dy[curdir];
			cnt++;
			//계속 이동
			move(cr, cc, nr, nc, curdir);
			
			//같은 방향이면 2칸씩 늘어남
			if (d == cnt) {
				cnt = 0;
				curdir = nextDir[curdir];
				check++;
			}
			if (check == 2) {
				check = 0;
				d++; // 이동해야 하는 칸만큼 2번 이동 했으면 이동해야 하는 칸 수 늘리기
			}
			cr = nr;
			cc = nc;
		}

	}
	
	private static void move(int cr, int cc, int nr, int nc, int curdir) {
		map[nr][nc]+=map[cr][cc];	//자리이동
		map[cr][cc]=0;	//모래 없어야하고
		
		int sandWeight = map[nr][nc];	//모래 질량
		int a = sandWeight;	//a칸에 들어갈 모래 질량
		int sr = 0;	//모래 흩날리는 행 좌표	
		int sc = 0;	//모래 흩날리는 열 좌표
		
		for(int i=0;i<9;i++) {
			sr = nr+dsx[curdir][i];
			sc = nc+dsy[curdir][i];
			int amount = (int)(sandWeight * (rate[i]*0.01));
			
			isInRange(sr, sc, amount);
			a -= amount;
		}
		
		int ar = nr+dsx[curdir][9];
		int ac = nc+dsy[curdir][9];
		isInRange(ar, ac, a);
		map[nr][nc]=0;
		
	}
	
	private static void isInRange(int x, int y, int amount) {
		if(x<0 || y<0 || x>=N || y>=N) sandout+=amount;
		else map[x][y] += amount;

	}

}