import java.util.*;

/*
내가 생각하기에 이건 그리디 => 이유는?
1. 멘토의 배열이 주어지지 않음. 즉 멘토는 내가 최적의 상태가 되게 배정해야함.
2. 멘토에 따라 대기 시간이 달라짐. 달라지는 시간을 토대로 가장 최적의 대기 시간을 구해야함

내가 생각한 풀이
1. 시각과 상담 시간을 이용하여 시작 시간과 종료 시간을 구한다.
2. 모든 유형에 멘토가 최소 1명은 있어야 하기 때문에, 모든 유형에 멘토를 먼저 배치하고 n-k만큼의 남은 인원을 배분한다.
    예) n이 5고 k가 3이면 모든 유형에 배치하고 남은 2명을 배분한다.
    이때 가능한 조합은 (2,0,0), (1,1,0), (1,0,1), (0,1,1), (0,2,0),(0,0,2)이다.
=> 이 방법은 완전 탐색 아닐까
*/

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE;

        // 타입마다 시간을 분리할 리스트
        List<List<Time>> timeForEachType = new ArrayList<>();
        for (int i = 0; i < k + 1; i++) {
            timeForEachType.add(new ArrayList<>());
        }

        // 1. 유형마다 시간을 분리
        for (int[] req : reqs) {
            int startTime = req[0];
            int duration = req[1];
            int type = req[2];

            timeForEachType.get(type).add(new Time(startTime, startTime + duration));
        }

        // 2. 각 유형[type]마다 1~(n-k)+1 수의 상담사를 배정하고, 상담사 숫자에 따른 각 대기 시간을 구함
        int[][] waitTimeForEachTime = new int[k + 1][n + 1];
        for (int type = 1; type < k + 1; type++) { // 유형

            // 해당 타입의 상담을 신청 지원자가 한 명도 없을 때
            if (timeForEachType.get(type).size() == 0) continue;

            // 상담원을 1 부터 배정
            for (int counselors = 1; counselors <= n; counselors++) {

                // counselorSize만큼 상담원을 가질 때의 대기시간을 구함
                int waitTime = calculationTime(timeForEachType.get(type), counselors);

                // 현재 상담원 인원으로 상담했을 때 대기시간 저장
                waitTimeForEachTime[type][counselors] = waitTime;
            }
        }

        // 3. 중복조합을 이용해 멘토 배분 경우의 수를 생성하고 최적의 대기 시간을 찾음
        int[] initialCounselors = new int[k + 1];
        Arrays.fill(initialCounselors, 1); // 각 유형에 최소 한 명씩 배정
        int remainingCounselors = n - k;
        answer = findOptimalDistribution(k, remainingCounselors, initialCounselors, waitTimeForEachTime, timeForEachType);

        return answer;
    }

    private int findOptimalDistribution(int k, int remaining, int[] currentCounselors, int[][] waitTimeForEachTime, List<List<Time>> timeForEachType) {
        return distribute(k, remaining, 1, currentCounselors, waitTimeForEachTime, timeForEachType);
    }

    private int distribute(int k, int remaining, int type, int[] currentCounselors, int[][] waitTimeForEachTime, List<List<Time>> timeForEachType) {
        if (type > k) {
            // 모든 유형에 대해 상담사 배치를 완료했을 때 대기 시간 계산
            int totalWaitTime = 0;
            for (int t = 1; t <= k; t++) {
                totalWaitTime += waitTimeForEachTime[t][currentCounselors[t]];
            }
            return totalWaitTime;
        }

        int minWaitTime = Integer.MAX_VALUE;
        for (int i = 0; i <= remaining; i++) {
            currentCounselors[type] += i;
            minWaitTime = Math.min(minWaitTime, distribute(k, remaining - i, type + 1, currentCounselors, waitTimeForEachTime, timeForEachType));
            currentCounselors[type] -= i;
        }
        return minWaitTime;
    }

    private int calculationTime(List<Time> typeTime, int counselorNumber) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 끝나는 시간 저장
        int waitTime = 0;

        for (Time t : typeTime) { // 해당 타입의 상담 정보들을 가져옴
            if (pq.isEmpty() || pq.size() < counselorNumber) { // 상담원수가 남아 있을 때
                pq.add(t.end);
            } else {
                // 현재 진행중인 상담중 가장 빨리 끝나는 시간
                int earlyConsultEndTime = pq.poll();

                if (t.start < earlyConsultEndTime) { // 대기시간이 있는 경우
                    waitTime += (earlyConsultEndTime - t.start);
                    pq.add(earlyConsultEndTime + (t.end - t.start));
                } else {
                    pq.add(t.end); // 대기시간이 없는 경우 종료시간 저장
                }
            }
        }
        return waitTime;
    }

    private class Time {
        int start, end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}