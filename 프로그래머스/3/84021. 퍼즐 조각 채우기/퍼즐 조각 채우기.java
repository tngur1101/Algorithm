import java.util.*;

class Solution {
    
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,-1,0,1};
    
    static class Point implements Comparable<Point>{
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Point o){
            int res = this.r-o.r;
            if(res==0) res=this.c-o.c;
            return res;
        }
    }
    
    static List<List<Point>> t = new ArrayList<>();
    static List<List<Point>> g = new ArrayList<>();
    
    static int len;
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        len = game_board.length;
        
        //game_board를 table과 같이 우리가 채워야 하는 공간을 1로 바꿔주기
        game_board = convertBoard(game_board);
        
        // print(game_board);
        
        boolean[][] table_v = new boolean[len][len];
        boolean[][] game_v = new boolean[len][len];
        
        getBlock(game_board, table, table_v, game_v);
        
        answer = compareBlock(t, g, answer);
        
        return answer;
    }
    
    public static int compareBlock(List<List<Point>> table, List<List<Point>> board, int answer){
        int tableSize = table.size();
        int boardSize = board.size();
        
        //블럭 채워졌는지 확인하는 거
        boolean[] visited = new boolean[boardSize];
        
        //총 key인 table 만큼 돌아야 하고 각 키 별 board를 다 돌아줘야 함
        //따라서 tableSize와 boardSize 순서가 바뀌면 안된다.
        for(int i=0;i<tableSize;i++){
            for(int j=0;j<boardSize;j++){
                //table의 i번째 블록의 크기와 board의 j번째 블록의 크기가 다르거나 이미 채워져있는 블록이면
                if(table.get(i).size()!=board.get(j).size() || visited[j])continue;
                if(rotate(table.get(i), board.get(j))){
                    //블록채워주고
                    visited[j]=true;
                    answer+=board.get(j).size();
                    break;
                }
            }
        }
        return answer;
    }
    
    public static boolean rotate(List<Point> table, List<Point> board){
        Collections.sort(board);
        
        //0, 90, 180, 270도
        for(int d=0;d<4;d++){
            Collections.sort(table);
            
            int curX = table.get(0).r;
            int curY = table.get(0).c;
            
            //회전하고 나면 각 좌표가 바뀔 것
            for(int j=0;j<table.size();j++){
                table.get(j).r -= curX;
                table.get(j).c -= curY;
            }
            
            boolean check = true;
            
            //일치 확인
            for(int i=0;i<board.size();i++){
                if(table.get(i).r!=board.get(i).r || table.get(i).c!=board.get(i).c){
                    check = false;
                    break;
                }
            }
            
            //회전
            //x,y->y,-x
            if(!check){
                for(int i=0;i<table.size();i++){
                    int temp = table.get(i).r;
                    table.get(i).r=table.get(i).c;
                    table.get(i).c=-temp;
                }
            }
            else{
                return true;
            }
            
        }
        return false;
    }
    
    public static void getBlock(int[][] game_board, int[][] table, boolean[][] table_v, boolean[][] game_v){
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(game_board[i][j]==1 && !game_v[i][j]){
                    // System.out.println("game_board bfs 시작");
                    g.add(bfs(i,j,game_board, game_v));
                }
                if(table[i][j]==1 && !table_v[i][j]){
                    // System.out.println("table bfs 시작");
                    t.add(bfs(i,j,table,table_v));
                }
            }
        }
    }
    
    public static List<Point> bfs(int x, int y, int[][] map, boolean[][] visited){
        visited[x][y]=true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));
        
        List<Point> temp = new ArrayList<>();
        temp.add(new Point(0,0));
        
        // System.out.println("처음 시작하는 좌표와 리스트 값: "+x+", "+y+", "+temp.get(0).r+", "+temp.get(0).c);
        
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];
                
                if(isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc]==1){
                    visited[nr][nc]=true;
                    q.offer(new Point(nr, nc));
                    // System.out.println("nr: "+nr+", nc: "+nc);
                    temp.add(new Point(nr-x, nc-y));
                    // System.out.println("list에 추가되는 값 : "+(nr-x)+", "+(nc-y));
                }
            }
        }
        return temp;
    }
    
    public static boolean isInRange(int x, int y){
        return x>=0 && y>=0 && x<len && y<len;
    }
    
    public static int[][] convertBoard(int[][] map){
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(map[i][j]==1)map[i][j]=0;
                else map[i][j]=1;
            }
        }
        return map;
    }
    
    public static void print(int[][] map){
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}