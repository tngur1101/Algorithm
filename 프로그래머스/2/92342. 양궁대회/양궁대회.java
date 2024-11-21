import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        int[] ans = {-1};
        int ansVal = 0;

        // 2^10 모든 조합 탐색 (비트마스크)
        for (int caseNum = 0; caseNum < (1 << 10); caseNum++) {
            int[] tans = new int[11];
            int currentCase = caseNum;

            // 비트마스크에 따라 화살 배치
            for (int point = 1; point <= 10; point++) {
                if ((currentCase & 1) == 1) {
                    tans[10 - point] = info[10 - point] + 1;
                }
                currentCase >>= 1;
            }

            // 총 화살 개수 초과 시 무시
            int sum = Arrays.stream(tans).sum();
            if (sum > n) continue;

            // 남은 화살을 0점에 배치
            tans[10] += n - sum;

            // 점수 계산
            int tansVal = 0;
            for (int point = 0; point <= 10; point++) {
                if (tans[10 - point] > info[10 - point]) {
                    tansVal += point;
                } else if (info[10 - point] > 0) {
                    tansVal -= point;
                }
            }

            // 점수 차이가 0 이하인 경우 무시
            if (tansVal <= 0) continue;

            // 최적의 결과 갱신
            if (tansVal > ansVal) {
                ans = tans.clone();
                ansVal = tansVal;
            } else if (tansVal == ansVal) {
                // 더 낮은 점수부터 화살을 많이 배치한 경우 갱신
                for (int i = 10; i >= 0; i--) {
                    if (tans[i] > ans[i]) {
                        ans = tans.clone();
                        break;
                    } else if (tans[i] < ans[i]) {
                        break;
                    }
                }
            }
        }
        
        return ans;
    }
}