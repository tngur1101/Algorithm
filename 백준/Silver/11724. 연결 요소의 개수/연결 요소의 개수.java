import java.util.Scanner;

public class Main {

	//graph
	static int[][] graph;
	//visited 배열
	static boolean[] visited;
	//정점의 개수
	static int n;
	//간선의 개수
	static int m;
	//연결요소의 개수
	static int cnt;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc  = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		graph = new int[1001][1001];
		visited = new boolean[1001];
		cnt = 0;
		
		for(int i=0;i<m;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			graph[x][y] = graph[y][x] = 1;
		}
		
		for(int i=1;i<=n;i++) {
			if(visited[i]==false) {
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	public static void dfs(int v) {
		if(visited[v]==true) {
			return;
		}
		
		visited[v]=true;
		for(int i=1;i<=n;i++) {
			if(graph[v][i]==1 && visited[i]==false) {
				dfs(i);
			}
		}
	}

}