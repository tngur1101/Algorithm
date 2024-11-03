import java.util.*;

/*
* 하나의 큐에서 원소를 추출 후 해당 원소를 다른 큐에 넣어 각 큐의 원소의 합이 같게 하자
* pop한번과 insert 한번은 1회 작업으로 간주

* 완전탐색?
* 1. 초기 각 큐들의 합을 구한다.
* 2. 각 큐들의 합을 비교한다.
* 만약 왼쪽의 값이 더 크면 왼쪽 값에서 pop하고 오른쪽에 insert하고 합 비교?
* 이때 만약 오른쪽 값이 더 커지면 값을 pop하고 왼쪽에 insert한 후 합 비교?

* 예시
* 초기 L의 합 : 14, 초기 R의 합 : 16
* 1회 : R에서 4를 pop한 후 L에 추가
* => R : 12, L : 18
* 2회 : L에서 3을 pop한 후 R에 추가
* => R : 15, L : 15
*/

class Solution {
    long lSum = 0, rSum = 0;
    Queue<Integer> lq = new LinkedList<>();
    Queue<Integer> rq = new LinkedList<>();
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        int cnt = 0;
        
        for(int i = 0; i < queue1.length; i++){
            lSum += queue1[i];
            rSum += queue2[i];
            lq.offer(queue1[i]);
            rq.offer(queue2[i]);
        }
        
        // if ((lSum + rSum) % 2 != 0) {
        //     return -1;
        // }
        
        while(lSum != rSum){
            if(cnt >= queue1.length * 2 + 2){
                return -1;
            }
            
            // 만약 왼쪽 큐의 합이 오른쪽 큐보다 크다면
            if(lSum > rSum){
                // 왼쪽에서 하나를 pop하고 오른쪽에 삽입
                int temp = lq.poll();
                rq.offer(temp);
                
                lSum -= temp;
                rSum += temp;
                cnt++;
            } else {
                int temp = rq.poll();
                lq.offer(temp);
                
                rSum -= temp;
                lSum += temp;
                cnt++;
            }
        }
        answer = cnt;
        
        return answer;
    }
}