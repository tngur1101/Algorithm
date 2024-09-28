class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        dfs(numbers, 0, 0, target);
        
        return answer;
    }
    
    // depth : 깊이
    private void dfs(int[] numbers, int depth, int sum, int target){
        if(depth == numbers.length){
            if(sum == target){
                answer++;
            }
            return;
        }
        
        dfs(numbers, depth+1, sum+numbers[depth], target);
        dfs(numbers, depth+1, sum-numbers[depth], target);
    }
}