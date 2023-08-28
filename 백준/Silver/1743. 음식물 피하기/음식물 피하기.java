import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	//통로
	static int[][] graph;
	//방문 확인
	static boolean[][] visited;
	
	//통로의 세로 길이(1<=n<=100)
	static int n;
	
	//통로의 가로 길이(1<=m<=100)
	static int m;
	
	//음식물 쓰레기의 개수
	static int k;
	
	//음식물 쓰레기의 크기
	static int cnt;
	
	//답 변수
	static int answer;
	//deltas
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		graph = new int[101][101];
		visited = new boolean[101][101];
		
		for(int i=0;i<k;i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			
			graph[r-1][c-1]=1;
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(visited[i][j]==false && graph[i][j]==1) {
					cnt = 0;
					bfs(i,j);
					answer = Math.max(answer, cnt);
				}
			}
		}
		System.out.println(answer);
	}
	
	public static void bfs(int x,int y) {
		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(x,y));
		visited[x][y] = true;
		cnt++;
		while(!queue.isEmpty()) {
			point node = queue.poll();
			
			for(int d = 0;d<4;d++) {
				int nx = node.x+dx[d];
				int ny = node.y+dy[d];
				
				//경계값 체크
				if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
				if(visited[nx][ny]==false && graph[nx][ny]==1) {
					queue.offer(new point(nx,ny));
					visited[nx][ny]=true;
					cnt++;
				}
			}
			
		}
	}
	
	static class point{
		int x;
		int y;
		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}