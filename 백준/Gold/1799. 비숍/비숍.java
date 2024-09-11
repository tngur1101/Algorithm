import java.util.*;
import java.io.*;

/**
 * 비숍을 놓을 수 있는 곳 : 1
 * 비숍을 놓을 수 없는 곳 : 0
 * 
 * N-Queen과는 다르게 비숍을 놓을 수 있는지 상하좌우를 탐색할 필요 x
 * 비숍은 대각선으로만 움직이니까 그 왼쪽 상단 끝에서 시작하는 애랑 그 바로 오른쪽에서 시작하는 애는 만날일이 없음
 * 그니까 실제 체스판 흰색이랑 검은색 부분 보면서 체크하면 될듯?
 * 
 */

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {-1, -1, 1, 1};
	static int[] dy = {-1, 1, -1, 1};
	static boolean[][] visited;
	static int[] answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = new int[2];
		
//		System.out.println("dfs 돌기 전 answer 배열");
//		System.out.println(Arrays.toString(answer));
		
//		printMap();
		visited = new boolean[N][N];
		dfs(0,0,0, true);
		
//		System.out.println("dfs 한번 돈 후 answer 배열");
//		System.out.println(Arrays.toString(answer));
		
		visited = new boolean[N][N];
		dfs(0,1,0, false);
		
//		System.out.println("dfs 2번 돈 후 answer 배열");
//		System.out.println(Arrays.toString(answer));
		
		System.out.println(answer[0] + answer[1]);
		
	}
	
	private static void dfs(int r, int c, int cnt, boolean isBlack) {
		if(isBlack) answer[0] = Math.max(answer[0], cnt);
		else answer[1] = Math.max(answer[1], cnt);
		
		if (c >= N) {
            r += 1;
            if (r % 2 == 0) {
                if (isBlack) c = 0;
                else c = 1;
            } else {
                if (isBlack) c = 1;
                else c = 0;
            }
        }
		
		if(r >= N) return;
		
		if(isAvailable(r, c)) {
			visited[r][c] = true;
			dfs(r,c+2, cnt+1, isBlack);
			visited[r][c] = false;
		}
		
		dfs(r, c+2, cnt, isBlack);
		
	}
	
	private static boolean isAvailable(int r, int c) {
		// 놓을 수 없는 곳이면 false 반환
		if(map[r][c] == 0) return false;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dx[d];
			int nc = c + dy[d];
			
			while (isInRange(nr, nc)) {
                if (visited[nr][nc]) return false;
                
                nr += dx[d];
                nc += dy[d];
            }
		}
		return true;
	}
	
	private static boolean isInRange(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
	
	private static void printMap() {
		for(int i = 0; i <N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}