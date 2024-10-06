import java.util.*;

class Solution {
    class Pos{
        int r,c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    int n,m;
    int[][] map;
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    boolean redEnd, blueEnd;
    boolean[][][] visited;
    public int solution(int[][] maze) {
        int answer = 0;
        
        n = maze.length;
        m = maze[0].length;
        
        map = new int[n][m];
        visited = new boolean[n][m][2];
        
        Pos red = null;
        Pos blue = null;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = maze[i][j];
                if(map[i][j] == 1) red = new Pos(i,j);
                else if(map[i][j] == 2) blue = new Pos(i,j);
            }
        }
        
        visited[red.r][red.c][0] = true;
        visited[blue.r][blue.c][1] = true;
        
        answer = dfs(red, blue, 0);
        
        if(answer == Integer.MAX_VALUE){
            return 0;
        }
        
        return answer;
    }
    
    private Pos move(int r, int c, int d){
        int nr = r + dx[d];
        int nc = c + dy[d];
        return new Pos(nr, nc);
    }
    
    private boolean isPossible(Pos red, Pos nred, Pos blue, Pos nblue){
        if(!isInRange(nred.r, nred.c) || !isInRange(nblue.r, nblue.c) || map[nred.r][nred.c] == 5 || map[nblue.r][nblue.c] == 5) return false;
        
        if(nred.r == nblue.r && nred.c == nblue.c) return false;
        
        if((red.r == nblue.r && red.c == nblue.c) && (blue.r == nred.r && blue.c == nred.c)) return false;
        
        if((!redEnd && visited[nred.r][nred.c][0]) || (!blueEnd && visited[nblue.r][nblue.c][1])) return false;
        
        return true;
    }
    
    private int dfs(Pos red, Pos blue, int result){
        if(redEnd && blueEnd){
            return result;
        }
        
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Pos nred = null;
                Pos nblue = null;
                
                if(redEnd){
                    nred = red;
                } else {
                    nred = move(red.r, red.c, i);
                }
                
                if(blueEnd){
                    nblue = blue;
                } else {
                    nblue = move(blue.r, blue.c, j);
                }
                
                if(!isPossible(red, nred, blue, nblue)) continue;
                
                visited[nred.r][nred.c][0] = true;
                visited[nblue.r][nblue.c][1] = true;
                
                if(map[nred.r][nred.c] == 3) redEnd = true;
                if(map[nblue.r][nblue.c] == 4) blueEnd = true;
                
                answer = Math.min(answer, dfs(nred, nblue, result+1));
                
                redEnd = false;
                blueEnd = false;
                visited[nred.r][nred.c][0] = false;
                visited[nblue.r][nblue.c][1] = false;
            }
        }
        
        return answer;
    }
    
    private boolean isInRange(int r, int c){
        return r >= 0 && c >= 0 && r < n && c < m;
    }
}