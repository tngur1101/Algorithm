import java.util.*;

/*
* 시침 : 1초 1/120도
* 분침 : 1초 0.1도
* 초침 : 1초 6도

* 시침이 한바퀴 도는 동안 초침은 720바퀴 돌음 => 그럼 719번 만남
* 분침이 한바퀴 도는 동안 초침으느 60번 => 59번 만남

* 분침 : sec % (3600 / 59)번
* 시침 : sec % (43200 / 719)번

* 분침 시침 모두 겹치는 경우 : 12시 정각
*/

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        int startSec = calToSec(h1, m1, s1);
        int endSec = calToSec(h2, m2, s2);
        
        answer = countAlarm(endSec) - countAlarm(startSec);
        
        if(isAlarmNow(startSec)) answer += 1;
        
        return answer;
    }
    
    private int calToSec(int h, int m, int s){
        return h * 3600 + m * 60 + s;
    }
    
    private boolean isAlarmNow(int sec){
        return sec * 59 / 3600 == 0 || sec * 719 % 43200 == 0;
    }
    
    private int countAlarm(int sec){
        int minteAlarm = sec * 59 / 3600;
        int hourAlarm = sec * 719 / 43200;
        
        int duplicate = 0;
        if(sec >= 43200){
            duplicate = 2;
        } else {
            duplicate = 1;
        }
        
        return minteAlarm + hourAlarm - duplicate;
    }
}