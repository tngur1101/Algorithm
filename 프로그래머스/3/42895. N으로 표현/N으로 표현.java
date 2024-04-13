import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;    // 최솟값이 8보다 크면 -1을 리턴하기 때문에 기본을 -1로 설정
        
        int selfNum = N;
        Set<Integer>[] nArr = new HashSet[9];
        
        // 각 배열에는 무조건 5, 55, 555가 있어야 함
        for(int i = 1; i < 9; i++){
            nArr[i] = new HashSet<>();
            nArr[i].add(selfNum);
            selfNum = selfNum*10 + N;
        }
        
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < i; j++){
                for(int a : nArr[j]){
                    for(int b : nArr[i-j]){
                        nArr[i].add(a+b);
                        nArr[i].add(a-b);
                        nArr[i].add(b-a);
                        nArr[i].add(a*b);
                        if(b != 0) {
                            nArr[i].add(a/b);
                        }
                        if(a != 0) {
                            nArr[i].add(b/a);
                        }
                    }
                }
            }
        }
        
        for(int i = 1; i < 9 ; i++){
            if(nArr[i].contains(number)){
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}