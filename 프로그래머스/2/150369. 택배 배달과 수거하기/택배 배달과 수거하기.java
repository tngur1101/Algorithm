import java.util.*;

/*
*   가장 최단 경로가 되기 위해서는 무조건 맨 뒤부터 배달을 하고 맨 뒤에부터 수거를 해와야 한다
*   그래야 맨 뒤를 갈 필요가 없어지면서 경로가 짧아지기 때문에
*   그렇다면 내가 선택해야 하는건 cap에 맞춰서 맨 뒤 집에 deliver를 먼저 해주고
*   돌아오는 길에 현재 위치 기준 가장 가까운 집에서 pickup을 한다.
*   그럼 for문을 뒤에서부터 돌면 되나?
*   현재 무게를 저장하는 변수도 필요
*/

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int deliver = 0;
        int pickup = 0;
        
        for(int i = n-1; i>=0; i--){
            int dist = 0;
            deliver += deliveries[i];
            pickup += pickups[i];
            
            while(deliver > 0 || pickup > 0){
                deliver -= cap;
                pickup -= cap;
                dist++;
            }
            
            answer += dist * 2 * (i+1);
        }
        
        return answer;
    }
}