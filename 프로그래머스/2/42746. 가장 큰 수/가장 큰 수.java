import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] strArr = new String[numbers.length];
        
        for(int i=0;i<numbers.length;i++){
            strArr[i]=String.valueOf(numbers[i]);
        }
        
        // System.out.println(Arrays.toString(arr));
        
        Arrays.sort(strArr, (o1,o2)->(o2+o1).compareTo(o1+o2));
        
        if(strArr[0].equals("0")) return "0";
        
        answer = String.join("", strArr);
        
        // stringbuilder를 출력하려면 string으로 바꿔줘야 하는데 이때는 sb.toString()을 사용한다
        return answer;
    }
}