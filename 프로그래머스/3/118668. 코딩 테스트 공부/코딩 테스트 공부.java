import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int goalA = 0, goalC = 0;
        
        for(int i = 0; i < problems.length; i++){
            goalA = Math.max(problems[i][0], goalA);
            goalC = Math.max(problems[i][1], goalC);
        }
        
        if(goalA <= alp && goalC <= cop) return 0;
        
        if(alp >= goalA) alp = goalA;
        if(cop >= goalC) cop = goalC;
        
        int[][] dp = new int[goalA+2][goalC+2];
        
        for(int i = alp; i <= goalA; i++){
            for(int j = cop; j <= goalC; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int i = alp; i <= goalA; i++){
            for(int j = cop; j <= goalC; j++){
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                
                for(int[] p: problems){
                    if(i >= p[0] && j >= p[1]){
                        if(i + p[2] > goalA && j + p[3] > goalC){
                            dp[goalA][goalC] = Math.min(dp[goalA][goalC], dp[i][j] + p[4]);
                        }
                        else if(i + p[2] > goalA){
                            dp[goalA][j+p[3]] = Math.min(dp[goalA][j+p[3]], dp[i][j]  + p[4]);
                        }
                        else if(j + p[3] > goalC){
                            dp[i + p[2]][goalC] = Math.min(dp[i + p[2]][goalC], dp[i][j] + p[4]);
                        }
                        else if(i + p[2] <= goalA && j + p[3] <= goalC){
                            dp[i+p[2]][j+p[3]] = Math.min(dp[i+p[2]][j+p[3]], dp[i][j] + p[4]);
                        }
                    }
                }
            }
        }
        
        
        return dp[goalA][goalC];
        
    }
}