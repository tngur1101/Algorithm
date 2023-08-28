import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,V;	//N: 정점의 개수, M : 간선의 개수, V:정점의 번호 
	static int[][] graph;	//인접 행렬
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b]=graph[b][a]=1;
		}
		
		visited = new boolean[N+1];
		dfs(V);
		sb.append(System.lineSeparator());
		bfs(V);
		System.out.println(sb);

	}
	
	private static void dfs(int num) {
		visited[num] = true;
		sb.append(num).append(" ");
		
		for(int i=1;i<=N;i++) {
			if(graph[num][i]==1 && !visited[i]) {
				dfs(i);
			}
		}
		
		
	}
	
	private static void bfs(int num) {
		visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(num);
		visited[num]=true;
		sb.append(num).append(" ");
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int i=1;i<=N;i++) {
				if(graph[current][i]==1 && !visited[i]) {
					queue.offer(i);
					visited[i]=true;
					sb.append(i).append(" ");
				}
			}
		}
	}
	
	private static void print() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	}

}