import java.util.*;

/*
* 음 우선 이 문제는 dfs + 백트래킹이라고 생각한다.
* 이유는 모든 노드를 탐색해야 하고
* 늑대의 수가 양과 같거나 많으면 더 갈 필요가 없기 때문이다.

* 내가 생각하는 문제 풀이
* 1. edges를 이용하여 우선 tree를 만든다.
* 2. dfs를 돌면서 모든 노드를 탐색한다.
* 3. 만약 양의 수가 늑대의 수보다 적거나 같다면 return한다.
* 근데 이거 왔다가 다시 갈 수 있어야 한다.
* 흠... 그럼 visited 배열은 쓰면 안될 것 같고
* 갈 수 있는 노드를 담은 무언가를 만들어서 계속 갱신을 해줘야 할 것 같다.
*/

class Solution {
    
    List<Integer>[] tree;
    int answer;
    
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        
        tree = new ArrayList[info.length];
        
        // tree 초기화
        for(int i = 0; i < info.length; i++){
            tree[i] = new ArrayList<>();
        }
        
        // edges를 돌면서 값 넣어주기
        for(int[] edge: edges){
            tree[edge[0]].add(edge[1]);
        }
        
        // 가능한 다음 노드를 넣어주는 리스트
        List<Integer> nextNodeList = new ArrayList<>();
        
        nextNodeList.add(0);
        
        dfs(0, 0, 0, info, nextNodeList);
        
        return answer;
    }
    
    /*
    * @idx : 현재 노드의 idx
    * @sheep : 양의 수
    * @wolf : 늑대의 수
    * @info : 노드들 배열
    * @next : 가능한 다음 노드들 리스트
    */
    private void dfs(int idx, int sheep, int wolf, int[] info, List<Integer> next){
        // 우선 info의 idx가 0인지 1인지 판단해서 sheep과 wolf의 수 갱신
        if(info[idx] == 0) sheep++;
        else wolf++;
        
        // System.out.println("idx: "+idx);
        // System.out.println("sheep: "+sheep);
        
        // 만약 sheep의 수가 wolf보다 작거나 같으면 return
        if(sheep <= wolf) return;
        
        // 답 갱신
        answer = Math.max(sheep, answer);
        
        // 업데이트용 리스트
        List<Integer> temp = new ArrayList<>();
        
        // 현재 노드가 아니면 넣어준다
        for(int node: next){
            if(node != idx){
                temp.add(node);
            }
        }
        
        // 그리고 tree의 리프노드를 확인해서
        // 리프노드가 아니라면 넣어준다.
        if (tree[idx] != null) {
            for (int t : tree[idx]) {
                temp.add(t);
            }
        }
        
        // 업데이트된 리스트를 이용해서 다음 노드를 찾는다
        for(int node: temp){
            dfs(node, sheep, wolf, info, temp);
        }
        
    }
}