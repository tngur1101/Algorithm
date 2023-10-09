import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,-1,0,1};
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		int maxHeight = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>maxHeight) maxHeight = map[i][j];
			}
		}
		
		int answer = 0;
		for(int height=0;height<maxHeight+1;height++) {
			visited = new boolean[N][N];
			int cnt = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]>height && !visited[i][j]) {
						cnt+= dfs(i,j,height);
					}
				}
			}
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);

	}
	
	private static int dfs(int r, int c, int height) {
		visited[r][c] = true;
		
		for(int d=0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!isInRange(nr, nc)) continue;
			if(visited[nr][nc])continue;
			
			if(map[nr][nc]>height) {
				dfs(nr,nc,height);
			}
		}
		return 1;
	}
	
	private static boolean isInRange(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}

}