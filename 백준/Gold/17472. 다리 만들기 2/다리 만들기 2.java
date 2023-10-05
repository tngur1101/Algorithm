import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,-1,0,1};
	
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parents;
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		int islandCnt=0;
		int bridgeCnt=0;
		int result=0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		print();
		
		// 맵 라벨링
		int mapLabel = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					bfs(new Node(i,j),mapLabel++);
					islandCnt++;
				}
			}
		}
		
//		System.out.println("bfs이후");
//		print();
		
		//길이 구하기
		visited = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]!=0){
                	//처음에는 여기서 문제라고 생각했는데
                    makeBridge(new Node(i,j),map[i][j]);
                }
            }
        }
        
        parents = new int[islandCnt+1];
        makeParents();
        
        //간선 연결
       while(!pq.isEmpty()){
            Edge temp = pq.poll();

            int a = find(temp.from);
            int b = find(temp.to);

            if(a==b) continue;

            union(a,b);
            result+=temp.weight;
            bridgeCnt++;
        }

        //모두 연결되어있는지 확인
        if(result==0 || bridgeCnt!=islandCnt-1){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
        
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot!=bRoot){
            parents[aRoot]=b;
        }
	}
	
	private static int find(int a) {
		if(parents[a]==a) return a;
		parents[a]=find(parents[a]);
		
		return parents[a];
	}
	
	private static void makeParents() {
		for(int i=0;i<parents.length;i++){
            parents[i]=i;
        }
	}
	
	//아마 이 함수에서 문제가 발생하는것 같아
	private static void makeBridge(Node node, int num) {
		int r = node.r;
		int c = node.c;
		int bridgeLen = 0;
		
		//상,좌,하,우
		for(int d=0;d<4;d++) {
			while(true) {
				r += dr[d];
				c += dc[d];
				
				//범위 안에 있다면
				if(isInRange(r, c)) {
					//만약 맵의 값이 num이랑 같으면 같은 섬이기 때문에 초기화
					if(map[r][c]==num) {
						bridgeLen=0;
						r=node.r;
                        c=node.c;
                        break;
                      
					}else if(map[r][c]==0) {	//바다면 다리 길이 증가
						bridgeLen++;
					}else {
						if(bridgeLen>1) {
							pq.add(new Edge(num, map[r][c], bridgeLen));
						}
						bridgeLen=0;
                        r=node.r;
                        c=node.c;
                        break;
					}
				}else {
					bridgeLen=0;
                    r=node.r;
                    c=node.c;
                    break;
				}
			}
		}
	}
	
	public static void bfs(Node node, int mapInt) {
		Queue<Node> q = new LinkedList<>();
		
		visited[node.r][node.c] = true;
		map[node.r][node.c]=mapInt;
		q.offer(node);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				//범위 안에 있고
				if(isInRange(nr, nc) && !visited[nr][nc]) {
					if(map[nr][nc]==1) {
						visited[nr][nc]=true;
						map[nr][nc]=mapInt;
						q.offer(new Node(nr,nc));
					}
				}
			}
		}
		
	}
	
	public static boolean isInRange(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
	
	public static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}