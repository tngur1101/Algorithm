import java.util.*;

class Point{
    int r;
    int c;
    public Point(int r, int c){
        this.r = r;
        this.c = c;
    }
}
class Solution {
    int N;
    int answer;
    Queue<Point>[] records;
    // int[][] map = new int[101][101];
    public int solution(int[][] points, int[][] routes) {
        N = routes.length;
        records = new LinkedList[N];
        
        for(int i = 0; i < N; i++){
            records[i] = new LinkedList<>();
        }
        
        recordRobotMoving(points, routes);
        countDangerousPlace();
        
        return answer;
    }
    
    private void recordRobotMoving(int[][] points, int[][] routes){
        for(int i = 0; i < N; i++){
            int[] route =  routes[i];
            int r = points[route[0] - 1][0];
            int c = points[route[0] - 1][1];
            
            records[i].offer(new Point(r,c));
            
            for(int j = 1; j < route.length; j++){
                int nr = points[route[j] - 1][0];
                int nc = points[route[j] - 1][1];
                
                while(nr != r){
                    if(nr > r) r++;
                    else r--;
                    
                    records[i].offer(new Point(r,c));
                }
                
                while(nc != c){
                    if(nc > c) c++;
                    else c--;
                    
                    records[i].offer(new Point(r,c));
                }
            }
        }
    }
    
    private void countDangerousPlace(){
        int cnt = 0;
        
        while(cnt < N){
            cnt = 0;
            int[][] map = new int[101][101];
            
            for(int i = 0; i < N; i++){
                if(records[i].isEmpty()){
                    cnt++;
                    continue;
                }
                
                Point p = records[i].poll();
                map[p.r][p.c]++;
            }
            
            for(int i = 0; i < 101; i++){
                for(int j = 0; j < 101; j++){
                    if(map[i][j] > 1) answer++;
                }
            }
        }
    }
}