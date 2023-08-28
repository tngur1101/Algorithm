import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point{
	int x;
	int y;
	int crash;
	public Point(int x, int y, int crash) {
		this.x = x;
		this.y = y;
		this.crash = crash;
	}
}

public class Main {
	
	static final int[] dx = {0,0,-1,1};
	static final int[] dy = {1,-1,0,0};
	
	static int[][] graph;
	static int n;
	static int m;
	static int[][][] visited;
	static int cnt;
	static Queue<Point> queue;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		visited = new int[n][m][2];
		graph = new int[n][m];
		for(int i=0;i<n;i++) {
			String tmp = sc.next();
			for(int j=0;j<m;j++) {
				graph[i][j] = Integer.valueOf(tmp.charAt(j)-'0');
			}
		}
		
		queue = new LinkedList<Point>();
		queue.offer(new Point(0,0,0));
		visited[0][0][0]=1;
		cnt = 1;
		
		System.out.println(bfs());
	}
	
	public static int bfs() {
		while(!queue.isEmpty()) {
			Point node = queue.poll();
			
			int cx = node.x;
			int cy = node.y;
			int crash = node.crash;
			if(cx == n-1 && cy==m-1) {
				return visited[cx][cy][crash];
			}
			
			for(int d=0;d<4;d++) {
				int nx = cx+dx[d];
				int ny = cy+dy[d];
				
				//경계선 체크
				if(nx>=0 && ny>=0 && nx<n && ny<m) {
					if(graph[nx][ny]==0 && visited[nx][ny][crash]==0) {
						visited[nx][ny][crash] = visited[cx][cy][crash]+1;
						queue.offer(new Point(nx,ny,crash));
					}
					if(graph[nx][ny]==1 && visited[nx][ny][crash]==0) {
						if(crash==0) {
							visited[nx][ny][1] = visited[cx][cy][crash]+1;
							queue.offer(new Point(nx,ny,1));
						}
						else if(crash==1) {
							continue;
						}
					}
				}
			}
		}
		return -1;
	}


}
