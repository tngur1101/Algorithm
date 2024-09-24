import java.util.*;
import java.io.*;

/**
 * 현재 map의 값보다 작은지 체크하면서 dfs를 수행한다.
 * 그리고 wayCnt라는 2차원 배열을 하나 더 선언한다.
 * 이 2차원 배열은 해당 지점까지 가는 루트의 개수를 저장하는 배열이다.
 * 
 * 그리하여 문제에서 요구하는 (M,N)의 값을 리턴하면 된다.
 * 즉, wayCnt[M][N]값이 답이 된다.
 */

public class Main {
	static int M,N;
	static int[][] map;
	static int[][] wayCnt;	// 해당 위치까지 갈 수 있는 경로의 개수를 저장하는 배열
//	static boolean[][] visited;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M+1][N+1];
		wayCnt = new int[M+1][N+1];
//		visited = new boolean[M+1][N+1];
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				wayCnt[i][j] = -1;
			}
		}
		
		// 왼쪽 위 시작점에서 시작하므로
//		visited[1][1] = true;
//		answer = dfs(1,1);
		dfs(1,1);
		
//		printWayCnt();
		answer = wayCnt[1][1];
		
		System.out.println(answer);
	}
	
	
	// r과 c는 시작지점
	private static void dfs(int r, int c) {
		// 만약 종점이면 wayCnt[r][c] = 1을 하고 리턴
		if(r == M && c == N) {
			wayCnt[r][c] = 1;
			return;
		}
		
		// 만약 계산한 값이 있다면 그냥 그 값 사용하면 되니까 리턴
		if(wayCnt[r][c] != -1) {
			return;
		}
		
		wayCnt[r][c] = 0;
		
		for(int d = 0; d < 4; d++) {
			// 다음 위치 계산
			int nr = r + dx[d];
			int nc = c + dy[d];
			
			if(isInRange(nr, nc) && map[nr][nc] < map[r][c]) {
				dfs(nr, nc);
				wayCnt[r][c] += wayCnt[nr][nc];
			}
		}
		
	}
	
//	private static int dfs(int r, int c) {
//		if(r == M && c == N) return 1;
//		
//		int pathCnt = 0;
//		
//		for(int d = 0; d < 4; d++) {
//			int nr = r + dx[d];
//			int nc = c + dy[d];
//			
//			if(isInRange(nr, nc) && map[nr][nc] < map[r][c]) {
//				pathCnt += dfs(nr, nc);
//			}
//		}
//		
//		return pathCnt;
//	}
	
	private static boolean isInRange(int r, int c) {
		return r >= 1 && c >= 1 && r <= M && c <= N;
	}
	
	private static void printWayCnt() {
		for(int i = 1; i <= M; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(wayCnt[i][j] +" ");
			}
			System.out.println();
		}
	}
}