import java.util.*;

/*
* 백트래킹?
* n개를 뽑는데 그 합이 s라면 그때만 재귀 호출 => 시간초과날듯
* 그럼 저 값을 n으로 나누면 될듯한데
* 만약 s가 n보다 작다면 -1을 리턴?
*/

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(s < n){
            return new int[]{-1};
        }
        
        int val = s / n;
        int mod = s % n;
        
        for(int i = 0; i < n; i++){
            answer[i] = val;
        }
        
        int idx = n-1;
        for(int m = 0; m < mod; m++){
            answer[idx]++;
            idx--;
        }
        
        return answer;
    }
    
}