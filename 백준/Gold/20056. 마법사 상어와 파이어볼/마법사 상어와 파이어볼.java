import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1. 일단 문제에서 하라는대로 하는데 맵을 fireball을 제네릭으로 갖는 2차원 리스트로 만들어서 관리하는것도 괜찮을듯 -> 그 이유는 fireball이 소멸되기도 하고 생기기도 하고 하기 때문에
 * 2. 방향으로 속력만큼 이동
 * 3. 합병
 * 4. 4등분 -> 질량 계산, 속력 계산, 방향 판별
 * 5. 질량 확인 후 0이면 소멸
 */

class Fireball{
	int r;	//행
	int c;	//열
	int m;	//질량
	int s;	//속력
	int d; //방향
	public Fireball(int r, int c, int m, int s, int d) {
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

public class Main {
	
	static int N,M,K;
	static ArrayList<Fireball> map[][];
	static ArrayList<Fireball> list = new ArrayList<>();
	
	//상,우상, 우,우하,하,좌하,좌,좌상
	static int dx[] = {-1,-1,0,1,1,1,0,-1};
	static int dy[] = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1][N+1];
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<N+1; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Fireball(r,c,m,s,d));
			list.add(new Fireball(r,c,m,s,d));
		}
		
//		print();
		
		//남은 이동횟수 K가 0보다 클동안 반복
		while(K-- > 0) {
			move();	//이동시키고
			//print();
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(map[i][j].size() >=2 ) {	//2개 이상이면
						merge(i,j);	//합병
						//print();
					}
				}
			}
			makeList();	//fireball list갱신
		}
		
		int answer = 0;
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(map[i][j].size() > 0) {
					for(Fireball fb : map[i][j]) {
						//System.out.println(cur.m);
						answer += fb.m;
						
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	//이동 함수
	//아이디어 : map함수 초기화 시켜준다음에 fireball list에서 파이어볼들의 위치를 바꿔주고 map에 추가해서 map을 갱신
	private static void move() {
		//일단 map함수를 초기화 시켜주고
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}
		
		for(Fireball fb: list) {
			fb.r = fb.r+dx[fb.d]*(fb.s%N);
			fb.c = fb.c+dy[fb.d]*(fb.s%N);
			
			//경계선 체크 얘는 괴상하게 N을 넘어가면 1로 돌아와야하고 0으로 넘어가면 N으로 돌아와야함
			if(fb.r>N) fb.r %= N;
			if(fb.c>N) fb.c %= N;
			if(fb.r<=0) fb.r = N- Math.abs(fb.r);
			if(fb.c<=0) fb.c = N- Math.abs(fb.c);
			
			map[fb.r][fb.c].add(fb);
		}
	}
	
	private static void merge(int r, int c) {
		int sumM = 0, sumS=0, sumCnt=0, sumD = 0;
		boolean isEven = true;	//모두 짝수인지
		boolean isOdd = true;	//모두 홀수인지
		
		for(Fireball fb: map[r][c]) {
			sumM += fb.m;	//질량 합쳐주고
			sumS += fb.s;	//속력 합쳐주고
			sumCnt++;
			if(fb.d%2==0)isOdd=false;	//짝수이므로
			else isEven=false;	//홀수이므로
		}
		
		int m = sumM/5;	//분할된 질량 계산
		int s = sumS/sumCnt;	//분할된 속도 계산
		
		map[r][c] = new ArrayList<Fireball>();
		if(m<=0)return;	//질량이 0이거나 0보다 작으면 return
		
		if(isEven||isOdd) {
			for(int d=0;d<4;d++) {	//모두 짝수이거나 모두 홀수인경우
				map[r][c].add(new Fireball(r,c,m,s,d*2));
			}
		}
		else {
			for(int d=0;d<4;d++) {	//아무것도 아닌 경우
				map[r][c].add(new Fireball(r, c, m, s, d*2+1));
			}
		}
	}
	
	//fireball 리스트 업데이트
	private static void makeList() {
		list = new ArrayList<>();
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				if(map[i][j].size()>0) {
					for(Fireball fb:map[i][j]) {
						list.add(fb);
					}
				}
			}
		}

	}
	
	//확인용 프린트 함수
	private void print() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				System.out.print(map[i][j].size()+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}