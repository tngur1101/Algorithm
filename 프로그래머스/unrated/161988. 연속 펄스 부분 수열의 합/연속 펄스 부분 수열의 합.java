class Solution {
    
    static long[] pulse, reversePulse;
    static long[] calPulse, calRPulse;
    static long[] dp, dp2;
    
    public long solution(int[] sequence) {
        long answer = 0;
        
        int len = sequence.length;
        pulse = new long[len];
        reversePulse = new long[len];
        dp = new long[len+1];
        dp2 = new long[len+1];
        calPulse = new long[len];
        calRPulse = new long[len];
        
        initPulse(len, sequence);
        calculatePulse(len, sequence);
        calculateDP(len);
        
        answer = getAnswer(len);
        return answer;
    }
    
    static void initPulse(int len, int[] sequence){
        for(int i=0;i<len;i++) {
			if(i%2==0) {
				pulse[i] = 1;
				reversePulse[i] = -1;
			}
			else {
				pulse[i] = -1;
				reversePulse[i] = 1;
			}
		}
    }
    
    static void calculatePulse(int len, int[] sequence){
        for(int i=0;i<len;i++) {
			calPulse[i] = sequence[i]*pulse[i];
			calRPulse[i] = sequence[i]*reversePulse[i];
		}
    }
    
    static void calculateDP(int len){
        for(int i=1;i<=len;i++) {
			dp[i] = Math.max(dp[i-1]+calPulse[i-1], calPulse[i-1]);
			dp2[i] = Math.max(dp2[i-1]+calRPulse[i-1], calRPulse[i-1]);
		}
    }
    
    static long getAnswer(int len){
        long answer = 0;
        for(int i=1;i<=len;i++){
            answer = Math.max(answer, Math.max(dp[i], dp2[i]));
        }
        
        return answer;
    }
    
}