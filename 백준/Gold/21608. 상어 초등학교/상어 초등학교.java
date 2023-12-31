import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Student implements Comparable<Student>{
	int r;
	int c;
	int likeCnt;
	int emptyCnt;
	public Student(int r, int c, int likeCnt, int emptyCnt) {
		this.r = r;
		this.c = c;
		this.likeCnt = likeCnt;
		this.emptyCnt = emptyCnt;
	}
	
	@Override
	public int compareTo(Student o) {
		if(this.likeCnt != o.likeCnt) return o.likeCnt - this.likeCnt;
		
		if(this.emptyCnt != o.emptyCnt) return o.emptyCnt - this.emptyCnt;
		
		if(this.r != o.r) return this.r - o.r;
		
		return this.c - o.c;
	}
}

class Node{
    int num;
    ArrayList<Integer>list;
    Node(int num, ArrayList<Integer>list){
        this.num = num;
        this.list = list;
    }
}

public class Main {
	
	static int n;
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1};
	static int[][] map;
	
	static ArrayList<Node> list[];
	static HashMap<Integer,ArrayList<Integer>>hashmap = new HashMap<>();
	
	static int answer = 0;
 	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n =Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		list = new ArrayList[n*n];
        for(int i=0; i<n*n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=1; i<=n; i++) {
            Arrays.fill(map[i], -1);
        }
        
        for(int i=0; i<n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            ArrayList<Integer>tmp = new ArrayList<>();
            for(int j=1; j<=4; j++) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }
            list[i].add(new Node(num,tmp));
            hashmap.put(num, tmp);
        }
        
        for(int i=0; i<n*n; i++) {
            Node a = list[i].get(0);
            find(a.num,a.list);
        }
        
        getHappiness();
        System.out.println(answer);

	}
	
	private static void find(int num, ArrayList<Integer>solveList) {
		ArrayList<Student> one = new ArrayList<Student>();
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				int likeCnt = 0;
				int emptyCnt = 0;
				
				if(map[i][j]!=-1) continue;
				for(int d=0;d<4;d++) {
					int nr = i+dx[d];
					int nc = j+dy[d];
					
					if(isInRange(nr, nc) && solveList.contains(map[nr][nc]))likeCnt++;
				}
				
				emptyCnt = getEmptyCnt(i, j);
				one.add(new Student(i, j,likeCnt, emptyCnt));
			}
		}
		
		Collections.sort(one);
		Student a = one.get(0);
		map[a.r][a.c]=num;

	}
	
	private static void getHappiness() {
		for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                int stNum = map[i][j];
                int cnt = 0;
                ArrayList<Integer> tmp = hashmap.get(stNum);
                
                for(int d=0; d<4; d++) {
                    int nr = i + dx[d];
                    int nc = j + dy[d];
                    
                    if(isInRange(nr,nc) && tmp.contains(map[nr][nc])) {
                        cnt++;
                    }
                }
                if(cnt ==0) {
                    answer+=0;
                }
                else if (cnt==1) {
                    answer+=1;
                }
                else if(cnt==2) {
                    answer+=10;
                }
                else if(cnt ==3) {
                    answer+=100;
                }
                else {
                    answer+=1000;
                }
            }
        }

	}
	
	private static int getEmptyCnt(int r, int c) {
		int cnt = 0;
		for(int d = 0;d<4;d++) {
			int nr = r+dx[d];
			int nc = c+dy[d];
			
			if(isInRange(nr, nc)&&map[nr][nc]==-1)cnt++;											
		}
		
		return cnt;
	}
	
	public static boolean isInRange(int x, int y) {
		return x>=1 && y>=1 && x<=n && y<=n;
	}

}