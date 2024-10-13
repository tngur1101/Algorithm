class Solution {
    int N;
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        N = diffs.length;
        
        long start = diffs[0];
        long end = 0;
        
        for(int i = 0; i < N; i++){
            end = (int)Math.max(end, diffs[i]);
        }
        
        answer = (int)end;
        
        while(start <= end){
            long mid = (start + end) / 2;
            if(check(mid, diffs, times, limit)){
                end = mid - 1;
                answer = (int) Math.min(mid, answer);
            } else {
                start = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean check(long value, int[] diffs, int[] times, long limit){
        long sum = 0;
        
        for(int i = 0; i < N; i++){
            if(diffs[i] <= value){
                sum += times[i];
            } else {
                sum += ((diffs[i] - value) * (times[i-1] + times[i]) + times[i]);
            }
        }
        return sum <= limit;
    }
}