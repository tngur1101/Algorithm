import java.util.*;

// top이 모두 0이라고 가정했을 때
// n : 0이면 n[0] = 1, n : 1이면 n[1] = 3, n : 2이면 n[2]=8, n : 3이면 n[3] = 16
// 위에서 나오는 점화식 n[i] = 3*n[i-1] - n[i-2]
// top이 모두 1이라고 가정했을 때
// n : 0이면 n[0] = 1, n : 1이면 n[1] = 4, n : 2이면 n[2] = 15, n : 3이면 n[3] = 56
// 위에서 나오는 점화식 n[i] = 4*n[i-1] - n[i-2]


class Solution {
    public int solution(int n, int[] tops) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 3 + tops[0];
        
        for(int i = 2; i <= n; i++){
            dp[i] = ((3+tops[i-1])*dp[i-1] - dp[i-2]) % 10007;
            
            if(dp[i] < 0){
                dp[i] += 10007;
            }
        }
        
        return dp[n];
    }
}