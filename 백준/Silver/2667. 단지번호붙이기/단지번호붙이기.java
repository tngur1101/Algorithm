import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //상,좌,하,우
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,-1,0,1};
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static class Node{
        int r;
        int c;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            visited = new boolean[N][N];

            for(int i=0;i<N;i++){
                char[] line = br.readLine().toCharArray();
                for(int j=0;j<N;j++){
                    map[i][j] = line[j]-'0';
                }
            }

            List<Integer> list = new ArrayList<>();

//            print();
            int num=1;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]!=0 && !visited[i][j]){
                        int cnt = bfs(new Node(i,j), num++);
                        list.add(cnt);
                    }
                }
            }

        Collections.sort(list);
        System.out.println(list.size());
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }

    }

    private static int bfs(Node node, int num){
        Queue<Node> q = new LinkedList<>();
        visited[node.r][node.c]=true;
        q.offer(node);
        int cnt=1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isInRnage(nr, nc) && !visited[nr][nc]){
                    if(map[nr][nc]!=0){
                        map[nr][nc]=num;
                        visited[nr][nc]=true;
                        q.offer(new Node(nr,nc));
                        cnt++;
                    }
                }
            }

        }
        return cnt;
    }

    private static boolean isInRnage(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    private static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}